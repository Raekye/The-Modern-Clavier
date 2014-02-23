#!/bin/sh

echo "Building..."
gradle build
echo

echo "Running..."
java -jar build/libs/scala.jar

echo
echo "Done."
