#!/bin/bash

# Determine the directory in which this convert.sh script lives
# - courtesy of http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
CONVERTER_TOOLBOX_HOME="$( cd "$( dirname "$0" )" && pwd )"

java -cp "$CONVERTER_TOOLBOX_HOME/lib/*" eu.ddmore.convertertoolbox.cli.Main -in $1 -out $2 -sn $3 -sv $4 -tn $5 -tv $6

