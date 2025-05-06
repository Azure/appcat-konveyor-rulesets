import os
import yaml
from llm_client_utils import initialize_llm_client

def extract_rules_and_conditions(base_path):
    rules = []

    # Walk through the directory to find YAML files
    for root, _, files in os.walk(base_path):
        for file in files:
            if file.endswith(".yaml"):
                file_path = os.path.join(root, file)
                with open(file_path, 'r', encoding='utf-8') as yaml_file:
                    try:
                        data = yaml.safe_load(yaml_file)
                        if isinstance(data, list):  # Check if the file contains rules
                            for rule in data:
                                if 'ruleID' in rule and 'when' in rule:
                                    rules.append({
                                        'ruleID': rule['ruleID'],
                                        'conditions': rule['when'],
                                        'filepath': file_path,
                                        'message': rule.get('message', ''),
                                        'description': rule.get('description', '')
                                    })
                    except yaml.YAMLError as e:
                        print(f"Error parsing {file_path}: {e}")

    return rules

def verify_code_snippets_against_rules(rules, report_path):
    mismatches = []

    with open(report_path, 'r', encoding='utf-8') as yaml_file:
        try:
            report_data = yaml.safe_load(yaml_file)
            for ruleset in report_data:
                for category in ['violations']:#, 'insights']:
                    if category in ruleset:
                        for rule_id, item in ruleset[category].items():
                            print(f"Rule ID: {rule_id}")
                            if 'incidents' in item:
                                combined_code_snippet = "\n".join(
                                    f"URI: {incident.get('uri', '')}\nCode Snippet: {incident.get('codeSnip', '')}"
                                    for incident in item['incidents']
                                )
                                message = item['incidents'][0].get('message', '') if item['incidents'] else ''
                                matching_rule = next((rule for rule in rules if rule['ruleID'] == rule_id), None)
                                if matching_rule and not verify_condition(matching_rule['conditions'], combined_code_snippet, message, matching_rule['description']):
                                    mismatches.append({
                                        'ruleID': rule_id
                                    })
        except yaml.YAMLError as e:
            print(f"Error parsing {report_path}: {e}")

    return mismatches

def verify_condition(conditions, code_snippet, message, description):
    # TODO Call LLM API to verify the conditions
    # print(f"Verifying conditions: {conditions}, message: {message}, description: {description}, code_snippet : {code_snippet}")

    messages = [
            {"role": "user", "content": "I use regex or greb to match code with rules, the rules has these properties \n\nConditions:\n" 
             + conditions
             + "\n\nMessage:\n" 
             + message + 
             "\n\nDescription:\n" 
             + description 
             + "\n\nnow I get the code snip result according to the rule" 
             + "\n\nCode Snippet:\n" 
             + code_snippet + "\n" 
             + "\n\nI want to verify if the code snip match the rule(match the condition or match the message description), if not match, point the which rule not match and give me a reason" 
             + "\n\nYou don't need to print detail analysis, just give me a simple reason if the result is not match to the rule. format like this:\n\n"
             + "Rule ID: <rule_id>\n Not match \n Reason: <reason>\n\n"
             + "\n\nNote: the rule condition has and, or and provider, for example, if it's an or type condition, only match one of them is ok, details info see here: https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md#rule-conditions "
            }
        ]
    params = {
            "model": "gpt-4o",
            "messages": messages,
        }

    client = initialize_llm_client()
    response = client.chat.completions.create(**params)
    reply = response.choices[0].message.content
    print(reply)

    return True

def verify_all_reports(base_path, rules):
    mismatches = []

    for root, dirs, files in os.walk(base_path):
        for dir_name in dirs:
            report_path = os.path.join(root, dir_name, "output.yaml")
            if "Aplicacao-Java" in report_path and os.path.exists(report_path):
                mismatches.extend(verify_code_snippets_against_rules(rules, report_path))

    return mismatches

def main():
    # # extract rule info
    # base_path = "d:\\code\\appcat\\appcat-konveyor-rulesets\\default\\generated"
    # rules = extract_rules_and_conditions(base_path)

    # with open("extracted_rules.txt", "w", encoding="utf-8") as output_file:
    #     for rule in rules:
    #         output_file.write(f"Rule ID: {rule['ruleID']}\n")
    #         output_file.write(f"Conditions: {rule['conditions']}\n")
    #         output_file.write(f"Filepath: {rule['filepath']}\n")
    #         output_file.write(f"Message: {rule['message']}\n")
    #         output_file.write(f"Description: {rule['description']}\n")
    #         output_file.write("-" * 80 + "\n")

    reports_base_path = "d:\\code\\appcat\\appcat-konveyor-rulesets\\java-migration-examples"
    with open("extracted_rules.txt", "r", encoding="utf-8") as rules_file:
        rules = []
        current_rule = {}
        for line in rules_file:
            line = line.strip()
            if line.startswith("Rule ID:"):
                if current_rule:
                    rules.append(current_rule)
                current_rule = {'ruleID': line.split(":", 1)[1].strip()}
            elif line.startswith("Conditions:"):
                current_rule['conditions'] = line.split(":", 1)[1].strip()
            elif line.startswith("Filepath:"):
                current_rule['filepath'] = line.split(":", 1)[1].strip()
            elif line.startswith("Message:"):
                current_rule['message'] = line.split(":", 1)[1].strip()
            elif line.startswith("Description:"):
                current_rule['description'] = line.split(":", 1)[1].strip()
        if current_rule:
            rules.append(current_rule)

    mismatches = verify_all_reports(reports_base_path, rules)

    if mismatches:
        print("Mismatches found:")
        for mismatch in mismatches:
            print(f"Rule ID: {mismatch['ruleID']}")
            print(f"Code Snippet: {mismatch['code_snippet']}")
            print(f"Expected Conditions: {mismatch['expected_conditions']}")
            print("-" * 80)
    else:
        print("All code snippets match their respective rule conditions.")
    
    # messages = [
    #         {"role": "user", "content": "who are you?"},
    #     ]
    # params = {
    #         "model": "gpt-4o",
    #         "messages": messages,
    #     }

    # client = initialize_llm_client()
    # response = client.chat.completions.create(**params)
    # reply = response.choices[0].message.content
    # print(reply)

if __name__ == "__main__":
    main()