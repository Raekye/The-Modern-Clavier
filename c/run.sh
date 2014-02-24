#!/bin/sh

echo "Started."

echo "Cleaning sorting..."
rm sorting_algorithms.o

echo "Compiling sorting algorithms..."
gcc -std=c99 -lm -o sorting_algorithms.o sorting_algorithms.c

echo "Running algorithms..."
./sorting_algorithms.o

echo
echo "Done."