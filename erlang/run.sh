#!/bin/sh

echo "Compiling..."
rebar compile

echo "Running sorting algorithms..."
erl -noshell -pa ebin deps/*/ebin -run clavier_algorithms_sorting main -s init stop

echo
echo "Done."