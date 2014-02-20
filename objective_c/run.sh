#!/bin/sh

echo "Started."

echo
echo "Cleaning sorting..."
rm sorting.o

echo "Compiling sorting..."
gcc $(gnustep-config --objc-flags) -std=c99 -o sorting.o sorting.m $(gnustep-config --base-libs)

echo "Running sorting..."
./sorting.o

echo
echo "Done sorting."

echo "Done."