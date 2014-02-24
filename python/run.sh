#!/bin/bash

PYTHON="python"
PYTHON_VER=`$PYTHON --version 2>&1`
if 	[[ $PYTHON_VER != Python\ 3* ]];
then
	PYTHON="python3"
	PYTHON_VER=`$PYTHON --version 2>&1`
fi
echo "Running sorting with $PYTHON_VER..."
$PYTHON sorting.py

echo
echo "Done."
