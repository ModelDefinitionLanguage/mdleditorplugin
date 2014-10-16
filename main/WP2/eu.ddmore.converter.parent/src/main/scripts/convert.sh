#!/bin/bash

CONVERTER_TOOLBOX_HOME=${pwd}

java -cp "$CONVERTER_TOOLBOX_HOME/lib/*" eu.ddmore.convertertoolbox.cli.Main -in $1 -out $2 -sn $3 -sv $4 -tn $5 -tv $6
