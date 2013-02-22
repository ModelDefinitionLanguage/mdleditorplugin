#!/bin/bash

qsub -q all.q@navtst-nonmem.mango.local -u mrogalski -cwd $1
