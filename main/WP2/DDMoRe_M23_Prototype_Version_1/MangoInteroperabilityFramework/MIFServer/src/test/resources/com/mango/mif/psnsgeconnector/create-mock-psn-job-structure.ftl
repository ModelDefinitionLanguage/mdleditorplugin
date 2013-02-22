#!/bin/bash

mkdir A

cd A

mkdir NM_Run
touch NM_Run/OUTPUT
echo "Your Job 1234 (\"job name\") has been submitted" > NM_Run/JobId

cd ..

mkdir B

cd B 

mkdir NM_Run
touch NM_Run/OUTPUT
echo "Your Job 1234 (\"job name\") has been submitted" > NM_Run/JobId


cd ..

mkdir C

cd C 

mkdir NM_Run
touch NM_Run/OUTPUT
echo "Your Job 1234 (\"job name\") has been submitted" > NM_Run/JobId


cd ..

mkdir D

cd D

mkdir NM_Run
touch NM_Run/OUTPUT
echo "Your Job 1234 (\"job name\") has been submitted" > NM_Run/JobId