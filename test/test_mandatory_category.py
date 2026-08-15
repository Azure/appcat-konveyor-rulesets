#!/usr/bin/env python3
"""
Rule Category Validation Tests

This module validates that all rules in specified directories contain the required 'category' field.

Required directories:
- default/generated/azure
- default/generated/openjdk*
- default/generated/cloud-readiness  
- default/generated/00-discovery
- default/generated/technology-usage
- default/generated/os

Valid category values: mandatory, potential, optional, information

Usage:
  python test_mandatory_category.py           # Run unit tests
  python -m unittest test_mandatory_category.py -v   # Run with verbose output
"""

import os
import sys
import yaml
import unittest
import glob
from pathlib import Path


class TestMandatoryCategoryFields(unittest.TestCase):
    """Test that rules in specified directories must contain category field"""
    
    def setUp(self):
        """Set up test environment"""
        self.base_dir = Path(__file__).parent.parent / "default" / "generated"
        
        # Define directories that must contain category field
        self.mandatory_category_dirs = [
            "azure",
            "openjdk*",  # Wildcard to match all openjdk directories
            "cloud-readiness",
            "00-discovery", 
            "technology-usage",
            "os"
        ]
        
    def get_target_directories(self):
        """Get all directories that need to be checked"""
        target_dirs = []
        
        for dir_pattern in self.mandatory_category_dirs:
            if "*" in dir_pattern:
                # Handle wildcards
                pattern_path = self.base_dir / dir_pattern
                matched_dirs = glob.glob(str(pattern_path))
                target_dirs.extend([Path(d) for d in matched_dirs])
            else:
                # Direct directory name
                dir_path = self.base_dir / dir_pattern
                if dir_path.exists():
                    target_dirs.append(dir_path)
                    
        return target_dirs
    
    def get_yaml_files(self, directory):
        """Get all YAML files in directory (excluding test files)"""
        yaml_files = []
        
        for ext in ["*.yaml", "*.yml"]:
            for file_path in directory.rglob(ext):
                # Skip test files
                if "test" in file_path.name.lower() or "tests" in str(file_path).lower():
                    continue
                yaml_files.append(file_path)
                
        return yaml_files
    
    def parse_yaml_rules(self, yaml_file):
        """Parse rules from YAML file"""
        try:
            with open(yaml_file, 'r', encoding='utf-8') as f:
                content = f.read()
                
            data = yaml.safe_load(content)
            
            if not isinstance(data, list):
                return []
                
            rules = []
            for item in data:
                if isinstance(item, dict) and 'ruleID' in item:
                    rules.append(item)
                    
            return rules
            
        except Exception as e:
            self.fail(f"Failed to parse YAML file {yaml_file}: {e}")
            
    def test_category_field_mandatory(self):
        """Test: All rules in specified directories must contain category field"""
        target_dirs = self.get_target_directories()
        
        # Ensure at least some directories were found
        self.assertGreater(len(target_dirs), 0, 
                          f"No target directories found. Base dir: {self.base_dir}")
        
        missing_category_files = []
        total_rules_checked = 0
        
        for target_dir in target_dirs:
            yaml_files = self.get_yaml_files(target_dir)
            
            for yaml_file in yaml_files:
                rules = self.parse_yaml_rules(yaml_file)
                total_rules_checked += len(rules)
                
                for rule in rules:
                    if 'category' not in rule:
                        rule_id = rule.get('ruleID', 'Unknown')
                        missing_category_files.append({
                            'file': str(yaml_file.relative_to(self.base_dir.parent.parent)),
                            'rule_id': rule_id,
                            'directory': target_dir.name
                        })
        
        # If there are files missing category, generate detailed error message
        if missing_category_files:
            error_msg = f"\n❌ CATEGORY VALIDATION FAILED ❌\n"
            error_msg += f"Found {len(missing_category_files)} rules missing 'category' field out of {total_rules_checked} total rules checked.\n\n"
            
            # Group by directory for display
            by_directory = {}
            for item in missing_category_files:
                dir_name = item['directory']
                if dir_name not in by_directory:
                    by_directory[dir_name] = []
                by_directory[dir_name].append(item)
            
            for dir_name, items in sorted(by_directory.items()):
                error_msg += f"📁 Directory: {dir_name} ({len(items)} missing)\n"
                for item in items[:5]:  # Show only first 5 to avoid too much info
                    error_msg += f"   • {item['file']} (Rule: {item['rule_id']})\n"
                if len(items) > 5:
                    error_msg += f"   ... and {len(items) - 5} more\n"
                error_msg += "\n"
                
            error_msg += "💡 REQUIRED ACTION:\n"
            error_msg += "All rules in these directories must have a 'category' field.\n"
            error_msg += "Valid categories: mandatory, potential, optional\n"
            
            self.fail(error_msg)
        else:
            print(f"\n✅ SUCCESS: All {total_rules_checked} rules in mandatory directories have 'category' field!")
    
    def test_valid_category_values(self):
        """Test: category field values must be valid"""
        valid_categories = ['mandatory', 'potential', 'optional']
        target_dirs = self.get_target_directories()
        
        invalid_category_files = []
        
        for target_dir in target_dirs:
            yaml_files = self.get_yaml_files(target_dir)
            
            for yaml_file in yaml_files:
                rules = self.parse_yaml_rules(yaml_file)
                
                for rule in rules:
                    if 'category' in rule:
                        category = rule['category']
                        if category not in valid_categories:
                            rule_id = rule.get('ruleID', 'Unknown')
                            invalid_category_files.append({
                                'file': str(yaml_file.relative_to(self.base_dir.parent.parent)),
                                'rule_id': rule_id,
                                'category': category,
                                'directory': target_dir.name
                            })
        
        if invalid_category_files:
            error_msg = f"\n❌ INVALID CATEGORY VALUES ❌\n"
            error_msg += f"Found {len(invalid_category_files)} rules with invalid 'category' values:\n\n"
            
            for item in invalid_category_files:
                error_msg += f"📄 {item['file']}\n"
                error_msg += f"   Rule ID: {item['rule_id']}\n"
                error_msg += f"   Invalid category: '{item['category']}'\n"
                error_msg += f"   Valid categories: {', '.join(valid_categories)}\n\n"
                
            self.fail(error_msg)


if __name__ == '__main__':
    unittest.main()