#!/bin/bash

SERVICE_HOME=$(pwd)

SERVICE_BINARY="converter-toolbox-1.0.0.jar"

CTS_LIBS_PATH="./lib,$(cd ..)/lib"
# This is a bit convoluted but this seemed to be the only way to get quoted paths with spaces in
# in the -D parameters treated as part of the same argument to the java call and not split into
# separate arguments on those spaces
eval  `echo  java -DAPP_HOME="$SERVICE_HOME" -Dcts.workingDirectory="$SERVICE_HOME/tmp" -Dloader.path="$CTS_LIBS_PATH" -jar ./$SERVICE_BINARY`
