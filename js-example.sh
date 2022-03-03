# BASH error handling:
#   exit on command failure
set -e

# Create File Output
sbt -error coreJS/fastLinkJS

# Create Server To Handle Request
netcat -l -t -p 8765 &
# nc -l 8765 & # For this flavor of nc

# Run the Javascript File Directly
node --enable-source-maps ./core/.js/target/scala-2.13/test-ember-timeout-fastopt/main.js
