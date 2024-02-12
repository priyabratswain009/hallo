config_file_path_dev = "./common-util/src/main/resources/security-property-files/InternalAccessTokenParameters.properties"

# Read the content of the original file
with open(config_file_path_dev, 'r') as file:
    file_content_dev = file.read()

# Define the strings to find and replace
find_str_dev = """url = http://localhost:9080/auth/realms/jhipster/protocol/openid-connect/token"""
replace_str_dev = """url = http://10.10.6.13:9080/auth/realms/jhipster/protocol/openid-connect/token"""

# Perform the replacement
if find_str_dev in file_content_dev:
    updated_content_dev = file_content_dev.replace(find_str_dev, replace_str_dev)
    # Write the modified content back to the file
    with open(config_file_path_dev, 'w') as file:
        file.write(updated_content_dev)
    print(f"Done! Replaced in {config_file_path_dev}")
else:
    print(f"String not found in {config_file_path_dev}. No replacements made.")
