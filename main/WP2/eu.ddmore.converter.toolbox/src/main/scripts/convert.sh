#!/bin/bash

# Determine the directory in which this convert.sh script lives
CONVERTER_TOOLBOX_HOME="$( cd "$( dirname "$0" )" && pwd )"

CONVERTER_LIBS_PATH="$(cd ..)/lib/*"

java -cp "$CONVERTER_TOOLBOX_HOME/lib/*:$CONVERTER_LIBS_PATH" eu.ddmore.convertertoolbox.cli.Main -in $1 -out $2 -sn $3 -sv $4 -tn $5 -tv $6

