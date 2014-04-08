#!/bin/sh

MAIN_CLASS="App"
if [[ ! -z "$1" ]]; then
	MAIN_CLASS="$1"
fi

echo "Cleaning..."
rm -rf build

echo "Preparing..."
mkdir -p build/classes/main

echo "Building..."
#gradle build
scalac -sourcepath src/main/scala -d build/classes/main "src/main/scala/com/creatifcubed/clavier/$MAIN_CLASS.scala"

echo "Running..."
#java -jar build/libs/scala.jar
scala -classpath build/classes/main "com.creatifcubed.clavier.$MAIN_CLASS"

echo
echo "Done."
