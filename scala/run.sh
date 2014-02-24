#!/bin/sh

echo "Cleaning..."
rm -rf build
echo

echo "Preparing..."
mkdir -p build/classes/main

echo "Building..."
#gradle build
scalac -sourcepath src/main/scala -d build/classes/main src/main/scala/com/creatifcubed/clavier/App.scala
echo

echo "Running..."
#java -jar build/libs/scala.jar
scala -classpath build/classes/main com.creatifcubed.clavier.App

echo
echo "Done."
