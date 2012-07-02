#!/bin/sh

java  -Dlog4j.configuration=file:log4j.properties -Dmif.client.userName=navplus -Dmif.client.password=[PLAIN-PASSWORD] -Dmif.rest.service.url="http://hendrix.mango.local:8080/MIFServer" -Dmif.broker.url="tcp://hendrix.mango.local:61616" -Dmif.shared.location=/usr/global/miftst-hendrix/hendrix-server/sharedfiles -cp ./MIFClient.jar:./lib/* com.mango.mif.client.cli.MIFInvoker ./example R_SGE
