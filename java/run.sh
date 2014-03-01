#!/bin/sh

echo "Cleaning..."
rm -rf target

echo "Preparing..."
mkdir target
mkdir target/classes

echo "Compiling..."
javac -d target/classes -g -source 1.7 -sourcepath src/main/java src/main/java/com/creatifcubed/clavier/SortingAlgorithms.java

echo "Running sorting algorithms..."
java -cp target/classes com.creatifcubed.clavier.SortingAlgorithms

echo
echo "Done."