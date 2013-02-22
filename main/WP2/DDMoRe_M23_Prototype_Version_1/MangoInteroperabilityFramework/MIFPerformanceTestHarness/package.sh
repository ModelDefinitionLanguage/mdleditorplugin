#!/bin/bash
################################################################################
#          FILE:  package.sh
#   DESCRIPTION:  Package up the executable JAR
################################################################################

mvn clean package shade:shade
