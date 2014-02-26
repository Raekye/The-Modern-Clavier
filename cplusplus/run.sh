#!/bin/sh

echo "Cleaning..."
rm sorting_algorithms.o

echo "Compiling sorting algorithms..."
g++ -g -std=c++11 -o sorting_algorithms.o sorting_algorithms.cpp

echo "Running sorting algorithms..."
./sorting_algorithms.o

echo
echo "Done."