import yaml
import os
import re

def validate_yaml_file(file_path):
    with open(file_path, 'r') as file:
        try:
            data = yaml.safe_load(file)
        except yaml.YAMLError as e:
            print(f"Error parsing {file_path}: {e}")
            return False

    return check_large_numbers(data, file_path)

def check_large_numbers(node, file_path, path=""):
    if isinstance(node, dict):
        for key, value in node.items():
            check_large_numbers(value, file_path, f"{path}/{key}")
    elif isinstance(node, list):
        for index, item in enumerate(node):
            check_large_numbers(item, file_path, f"{path}[{index}]")
    elif isinstance(node, int) and len(str(node)) >= 15:
        print(f"Error: Unquoted number with 15+ digits found at {path} in file {file_path}: {node}")
        raise ValueError("Unquoted large number detected!")

def validate_all_yaml_files():
    yaml_files = [os.path.join(dp, f) for dp, dn, filenames in os.walk(".") for f in filenames if f.endswith((".yaml", ".yml"))]
    for yaml_file in yaml_files:
        try:
            validate_yaml_file(yaml_file)
        except Exception as e:
            print(f"Validation failed for {yaml_file}: {e}")
            exit(1)

if __name__ == "__main__":
    validate_all_yaml_files()
