#!/usr/bin/env python3
"""
Extract distinct 'domain' and 'category' label values from YAML rule files under specified patterns.

Usage:
    python util/extract_labels.py [--base-dir PATH] [--out FILE]

By default searches:
 - default/generated/azure
 - default/generated/openjdk*
 - default/generated/cloud-readiness
 - default/generated/00-discovery
 - default/generated/technolog*
 - default/generated/os

Outputs JSON to stdout or to --out file.
"""

import argparse
import json
import re
import sys
from pathlib import Path

DEFAULT_PATTERNS = [
    "default/generated/azure",
    "default/generated/openjdk*",
    "default/generated/cloud-readiness",
    "default/generated/00-discovery",
    "default/generated/technolog*",
    "default/generated/os",
]


def extract_from_text(text):
    """Return (domains_set, categories_set) found in the provided YAML text.

    This does not require an external YAML parser and works by scanning for
    `labels:` lists in common YAML formats (block lists and inline lists).
    """
    domains = set()
    categories = set()
    lines = text.splitlines()
    i = 0
    while i < len(lines):
        line = lines[i]
        # Inline form: labels: [a, b, c]
        m_inline = re.match(r'^\s*labels\s*:\s*\[(.*)\]\s*$', line)
        if m_inline:
            items_str = m_inline.group(1)
            # split by comma but tolerate quoted values (basic)
            items = [it.strip() for it in re.split(r',(?=(?:[^\"\']*[\"\'][^\"\']*[\"\'])*[^\"\']*$)', items_str) if it.strip()]
            for item in items:
                _process_item(item.strip().strip('"').strip("'"), domains, categories)
            i += 1
            continue

        # Block form:
        # labels:
        # - a
        # - b
        m_block = re.match(r'^\s*labels\s*:\s*$', line)
        if m_block:
            j = i + 1
            while j < len(lines):
                m_item = re.match(r'^\s*-\s*(.*\S)\s*$', lines[j])
                if m_item:
                    item = m_item.group(1).strip()
                    _process_item(item, domains, categories)
                    j += 1
                    continue
                # allow blank lines or comments between items
                if re.match(r'^\s*$', lines[j]) or re.match(r'^\s*#', lines[j]):
                    j += 1
                    continue
                break
            i = j
            continue

        i += 1

    return domains, categories


def _process_item(item, domains, categories):
    # items like 'domain=cloud-readiness' or 'category=default-encoding'
    if '=' in item:
        k, v = item.split('=', 1)
        k = k.strip()
        v = v.strip().strip('"').strip("'")
        if k == 'domain':
            domains.add(v)
        elif k == 'category':
            categories.add(v)


def find_yaml_files(base_dir, patterns):
    base = Path(base_dir)
    files = set()
    for pat in patterns:
        for match in base.glob(pat):
            if match.is_dir():
                for f in match.rglob('*.yaml'):
                    files.add(f)
                for f in match.rglob('*.yml'):
                    files.add(f)
            elif match.is_file():
                if match.suffix.lower() in ('.yaml', '.yml'):
                    files.add(match)
    return sorted(files)


def main():
    parser = argparse.ArgumentParser(description="Extract domain and category values from rule YAML files.")
    parser.add_argument('--base-dir', '-b', default='.', help='Repository base directory (default: .)')
    parser.add_argument('--out', '-o', help='Write JSON output to file instead of stdout')
    parser.add_argument('--patterns', '-p', nargs='*', help='Glob patterns to search (relative to base_dir).')
    args = parser.parse_args()

    patterns = args.patterns if args.patterns else DEFAULT_PATTERNS
    files = find_yaml_files(args.base_dir, patterns)
    if not files:
        print("No files found for patterns: {}".format(patterns), file=sys.stderr)
        sys.exit(2)

    all_domains = set()
    all_categories = set()
    for f in files:
        try:
            text = f.read_text(encoding='utf-8')
        except Exception:
            try:
                text = f.read_text(encoding='latin-1')
            except Exception:
                # skip unreadable files
                continue
        d, c = extract_from_text(text)
        all_domains.update(d)
        all_categories.update(c)

    result = {
        'domains': sorted(all_domains),
        'categories': sorted(all_categories),
        'files_scanned': [str(x) for x in files]
    }
    out_json = json.dumps(result, indent=2, ensure_ascii=False)
    if args.out:
        Path(args.out).write_text(out_json, encoding='utf-8')
    else:
        print(out_json)


if __name__ == '__main__':
    main()
