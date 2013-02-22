#!/bin/sh

mifBrokerUrl="tcp://hendrix.mango.local:61616"
userName="[USER_NAME]"
userPassword="[SET_UNENCRYPTED_PASSWORD"
mifSharedLocation="/usr/global/miftst-hendrix/hendrix-server/sharedfiles"
dataDir=$1
scriptFile=$2
executionType=$3
executable=$4

java -Dlog4j.configuration="file:$(pwd)/lib/log4j.properties" -Dmif.client.userName=$userName -Dmif.client.password=$userPassword -Dmif.broker.url=$mifBrokerUrl -Dmif.shared.location=$mifSharedLocation -cp ./MIFClient.jar:./lib/* com.mango.mif.client.cli.MIFInvoker $dataDir $scriptFile $executionType $executable
