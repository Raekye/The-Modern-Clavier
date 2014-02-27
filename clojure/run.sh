#!/bin/sh

CLOJURE_JAR=$(grep -oh "[a-zA-Z0-9_/-]*\\.jar" $(which clojure))
CLOJURE_CONTRIB_JAR="${CLOJURE_JAR:0:-4}-contrib.jar"
echo "Guessing clojure at $CLOJURE_JAR, contrib at $CLOJURE_CONTRIB_JAR."

if [ -f $CLOJURE_JAR ];
then
	echo "Found $CLOJURE_JAR."
else
	read -p "Clojure jar not found. Please enter path: " CLOJURE_JAR
fi
if [ -f $CLOJURE_CONTRIB_JAR ];
then
	echo "Found $CLOJURE_CONTRIB_JAR."
else
	read -p "Clojure contrib jar not found. Please enter path: " CLOJURE_CONTRIB_JAR
fi

echo "Running sorting algorithms..."

java -cp $CLOJURE_JAR:$CLOJURE_CONTRIB_JAR clojure.main sorting_algorithms.clj

echo
echo "Done."