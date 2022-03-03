# BASH error handling:
#   exit on command failure
set -e

# Create Fatjar
sbt -error coreJVM/assembly

# Create Server To Handle Request
netcat -l -t -p 8765 &
# nc -l 8765 & # For this flavor of nc

java -jar core/.jvm/target/scala-2.13/example.jar