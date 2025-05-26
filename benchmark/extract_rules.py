import glob
import os
import yaml
from llm_client_utils import initialize_llm_client

def extract_file_extensions_from_conditions(conditions_yaml):
    import re
    import yaml
    exts = set()
    try:
        cond = yaml.safe_load(conditions_yaml)
        def find_patterns(obj):
            if isinstance(obj, dict):
                for k, v in obj.items():
                    if k == 'java.dependency':
                        exts.add('pom.xml')
                    elif k == 'java.referenced':
                        exts.add('java')
                    elif k == 'builtin.xml':
                        exts.add('xml')
                    elif k == 'filePattern' or k == 'filepattern':
                        if isinstance(v, str):
                            m = re.findall(r'\*\*?\/*\.(\w+)', v)
                            exts.update(m)
                            # 新增：支持 .(properties|yaml|yml) 这种写法
                            m2 = re.findall(r'\.\(([^)]+)\)', v)
                            for ext_group in m2:
                                for ext in ext_group.split('|'):
                                    if ext and ext not in ['$', '^']:
                                        exts.add(ext)
                        elif isinstance(v, list):
                            for item in v:
                                m = re.findall(r'\*\*?\/*\.(\w+)', item)
                                exts.update(m)
                                m2 = re.findall(r'\.\(([^)]+)\)', item)
                                for ext_group in m2:
                                    for ext in ext_group.split('|'):
                                        if ext and ext not in ['$', '^']:
                                            exts.add(ext)
                    elif k == 'builtin.file':
                        # 处理 builtin.file 的 pattern 字段
                        if isinstance(v, dict):
                            pattern = v.get('pattern', '')
                            # 匹配 *.ext 或 ^.*\.ext$
                            m = re.findall(r'\\.([a-zA-Z0-9]+)\$?', pattern)
                            if m:
                                exts.update(m)
                            # 匹配具体文件名如 pom.xml
                            m2 = re.findall(r'([a-zA-Z0-9_\-]+\.[a-zA-Z0-9]+)', pattern)
                            if m2:
                                exts.update(m2)
                    else:
                        find_patterns(v)
            elif isinstance(obj, list):
                for item in obj:
                    find_patterns(item)
        find_patterns(cond)
        if exts:
            return list(exts)
    except Exception as e:
        pass
    return ['txt']

def generate_testcase_files_for_rule(rule, output_dir):
    import re
    import os
    rule_id = rule['ruleID']
    base_name = os.path.splitext(os.path.basename(rule['filepath']))[0]
    file_exts = extract_file_extensions_from_conditions(rule['conditions'])
    # If both 'yaml' and 'yml' are present, keep only 'yaml'
    if 'yaml' in file_exts and 'yml' in file_exts:
        file_exts = [ext for ext in file_exts if ext != 'yml']
    print(f"Extracted file extensions for rule {rule_id}: {file_exts}")
    if not file_exts:
        file_exts = ['txt']  # 默认txt

    rule_dir = os.path.join(output_dir, f"{base_name}.{rule_id}")

    for ext in file_exts:
        # 针对不同类型，定制 prompt
        if ext == 'pom.xml':
            type_hint = "If the file type is pom.xml, the content must be a valid Maven pom.xml file, do not include any Java code or non-XML content."
        elif ext == 'java':
            type_hint = "If the file type is java, the content must be a valid Java source file, do not include any XML or configuration content."
        elif ext in ['properties', 'yaml', 'yml', 'txt']:
            type_hint = f"If the file type is {ext}, the content must be a valid {ext} configuration file, do not include any Java code or XML content."
        else:
            type_hint = ""

        prompt = f"""
You are a cloud migration rule testcase generator. Based on the following rule information, automatically generate the content for two testcase files:
1. .true: The content should trigger (match) this rule.
2. .false: The content should NOT trigger (not match) this rule.

{type_hint}

The rule condition info is provided in https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md#rule-conditions

Rule information:
RuleID: {rule['ruleID']}
Conditions: {rule['conditions']}
Message: {rule['message']}
Description: {rule['description']}

Please strictly output in the following format:
---true---
<content for true file>
---false---
<content for false file>
"""
        messages = [
            {"role": "user", "content": prompt}
        ]
        params = {
            "model": "gpt-4o",
            "messages": messages,
        }
        client = initialize_llm_client()
        response = client.chat.completions.create(**params)
        reply = response.choices[0].message.content

        # 解析 LLM 输出
        true_content = ""
        false_content = ""
        true_match = re.search(r"---true---\n(.*?)---false---", reply, re.DOTALL)
        false_match = re.search(r"---false---\n(.*)", reply, re.DOTALL)
        if true_match:
            true_content = true_match.group(1).strip()
        if false_match:
            false_content = false_match.group(1).strip()

        true_dir = f"{rule_dir}.true"
        false_dir = f"{rule_dir}.false"
        os.makedirs(true_dir, exist_ok=True)
        os.makedirs(false_dir, exist_ok=True)
        if ext == 'pom.xml':
            true_file = os.path.join(true_dir, "pom.xml")
            false_file = os.path.join(false_dir, "pom.xml")
        else:
            true_file =os.path.join(true_dir, f"true.{ext}")
            false_file = os.path.join(false_dir, f"false.{ext}")
        with open(true_file, "w", encoding="utf-8") as f:
            f.write(true_content)
        with open(false_file, "w", encoding="utf-8") as f:
            f.write(false_content)
        print(f"Testcase files generated: {true_file}, {false_file}")

def extract_rule_from_yaml(yaml_path):
    """
    从yaml文件中提取第一条规则的关键信息，返回dict。
    """
    with open(yaml_path, 'r', encoding='utf-8') as f:
        data = yaml.safe_load(f)
        if isinstance(data, list) and data:
            rule = data[0]
            return {
                'ruleID': rule.get('ruleID', ''),
                'conditions': yaml.dump(rule.get('when', '')),  # 保留原始结构
                'message': rule.get('message', ''),
                'description': rule.get('description', ''),
                'filepath': yaml_path
            }
    return None

def extract_rules_from_yaml(yaml_path):
    """
    从yaml文件中提取所有规则，返回dict列表。
    """
    rules = []
    with open(yaml_path, 'r', encoding='utf-8') as f:
        data = yaml.safe_load(f)
        if isinstance(data, list):
            for rule in data:
                rules.append({
                    'ruleID': rule.get('ruleID', ''),
                    'conditions': yaml.dump(rule.get('when', '')),  # 保留原始结构
                    'message': rule.get('message', ''),
                    'description': rule.get('description', ''),
                    'filepath': yaml_path
                })
    return rules

# 示例：如何为 01-azure-aws-config.yaml 规则生成测试用例（批量处理）
if __name__ == "__main__":

    yaml_folder = "d:/code/appcat/appcat-konveyor-rulesets/default/generated/azure"
    output_base_dir = "d:/code/appcat/appcat-konveyor-rulesets/benchmark/testcases"

    # 列出所有yaml文件
    yaml_files = glob.glob(os.path.join(yaml_folder, "*.yaml"))

    for yaml_path in yaml_files:
        base_name = os.path.splitext(os.path.basename(yaml_path))[0]
        output_dir = os.path.join(output_base_dir, base_name)
        rules = extract_rules_from_yaml(yaml_path)
        if base_name in ["01-azure-aws-config"]:
            continue
        for rule in rules:
            print(f"Processing rule: {rule['ruleID']} from {yaml_path}")
            generate_testcase_files_for_rule(rule, output_dir)