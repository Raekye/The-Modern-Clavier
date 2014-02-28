#!/bin/sh

echo "Building with cabal..."
cabal configure && cabal build

echo "Running..."
./dist/build/clavier/clavier

echo
echo "Done."