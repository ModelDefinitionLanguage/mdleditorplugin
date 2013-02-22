#!/bin/bash
source sge.sh
bootstrap bstest.mod -run_on_sge -samples=10 -dir=bs
