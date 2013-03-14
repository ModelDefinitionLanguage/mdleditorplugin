#!/bin/bash

source sge.sh

mkdir A

cd A

echo '#!/bin/bash
sleep 60
echo "job A result"' > Test-MIF-script.sh
chmod +x Test-MIF-script.sh
mkdir NM_Run
echo "" > NM_Run/OUTPUT
qsub -u ${userName} -wd ${workingDir}/A ${workingDir}/A/Test-MIF-script.sh | tee NM_Run/JobId


cd ..

mkdir B

cd B 

echo '#!/bin/bash
sleep 90
echo "job B result"' > Test-MIF-script.sh
chmod +x Test-MIF-script.sh
mkdir NM_Run
echo "" > NM_Run/OUTPUT
qsub -u ${userName} -wd ${workingDir}/B ${workingDir}/B/Test-MIF-script.sh | tee NM_Run/JobId



cd ..

mkdir C

cd C 

echo '#!/bin/bash
sleep 120
echo "job C result"' > Test-MIF-script.sh
chmod +x Test-MIF-script.sh
mkdir NM_Run
echo "" > NM_Run/OUTPUT
qsub -u ${userName} -wd ${workingDir}/C ${workingDir}/C/Test-MIF-script.sh | tee NM_Run/JobId


cd ..

mkdir D

cd D

echo '#!/bin/bash
sleep 150
echo "job D result"' > Test-MIF-script.sh
chmod +x Test-MIF-script.sh
mkdir NM_Run
echo "" > NM_Run/OUTPUT
qsub -u ${userName} -wd ${workingDir}/D ${workingDir}/D/Test-MIF-script.sh | tee NM_Run/JobId