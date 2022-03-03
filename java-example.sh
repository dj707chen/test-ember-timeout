sbt -error coreJVM/assembly


netcat -l -t -p 8765 &
java -jar core/.jvm/target/scala-2.13/example.jar