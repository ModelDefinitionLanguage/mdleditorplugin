#!/bin/bash

<#--
  Prepare Handler Freemarker Template

  SUBMIT_HOST_PREAMBLE      any series of variable assignments and commands that you want executed before the real work starts
  MANGO_UTILS          the directory containing the mango utility shell scripts
  CONNECTOR_UTILS      the directory containing the publishHandler.sh script, which is connector specific
  sgeSharedLocation    the grid shared directory we are copying to
  job                  the current job
-->

# Bail on the first error
set -e

${SUBMIT_HOST_PREAMBLE!}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

prepareHandler.sh \
    -d "${job.gridJobDirectory}"\
    -R "${sharedLocationManager.getRequestInputDirectory(job.jobId)}" "${job.gridJobDirectory}"

exit 0
