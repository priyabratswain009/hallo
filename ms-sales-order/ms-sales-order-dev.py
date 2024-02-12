config_file_path = "./src/main/resources/config/application-dev.yml"

# Read the content of the original file
with open(config_file_path, 'r') as file:
    file_content = file.read()

# Define the strings to find and replace
find_str = """  #  cors:
  #    allowed-origins: "http://localhost:8099,https://localhost:8099"
  #    allowed-methods: "GET"
  #    allowed-headers: "*"
  #    exposed-headers: "Authorization,Link,X-Total-Count"
  #    allow-credentials: true
  #    max-age: 1800"""
replace_str = """  cors:
    allowed-origins: "http://localhost:9000,https://localhost:9000,http://10.10.6.13:3000,http://10.10.6.26:3000"
    allowed-methods: "*"
    allowed-headers: "*"
    exposed-headers: "Authorization,Link,X-Total-Count"
    allow-credentials: true
    max-age: 1800"""

# Perform the replacement
updated_content = file_content.replace(find_str, replace_str)

# Write the modified content back to the file
with open(config_file_path, 'w') as file:
    file.write(updated_content)

print("Done!")
