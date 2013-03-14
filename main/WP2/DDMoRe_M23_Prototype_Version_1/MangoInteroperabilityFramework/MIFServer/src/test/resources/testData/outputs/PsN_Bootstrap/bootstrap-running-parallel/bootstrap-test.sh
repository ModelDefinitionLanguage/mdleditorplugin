#!/bin/bash
source sge.sh
bootstrap scm_test.mod -run_on_sge -samples=10 -dir=bs
