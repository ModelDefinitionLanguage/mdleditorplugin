#!/bin/bash

################################################################################
#          FILE:  migrate.sh
#   DESCRIPTION:  Migrate database using a given script.
################################################################################

if [ $# -ne 1 ]
then
  echo "Usage: `basename $0` {sqlscript}"
  exit $E_BADARGS
fi

mysql -u root -p -h localhost MIF < $1