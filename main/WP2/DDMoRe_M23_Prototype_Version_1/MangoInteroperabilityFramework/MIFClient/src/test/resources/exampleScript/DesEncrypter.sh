#!/bin/sh

JAVA_OPTS=" -Dlog4j.configuration=file:$(pwd)/lib/log4j.properties -cp ./MIFClient.jar:./lib/* com.mango.mif.utils.encrypt.DesEncrypterCLI "

options=()
options+=("$1")
options+=("$2")

if [ ! -z $3 ]; then
    options+=("$3")
fi

echo "$JAVA_OPTS"
echo "$PROGRAM_OPTS"
java $JAVA_OPTS "${options[@]}"
