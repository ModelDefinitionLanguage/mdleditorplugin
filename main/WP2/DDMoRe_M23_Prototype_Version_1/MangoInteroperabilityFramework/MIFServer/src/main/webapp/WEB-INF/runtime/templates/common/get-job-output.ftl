#!${SHELL}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/getoutput.ftl" ${job.getJobId()}

logMetrics "${job.getJobId()}" "common/getoutput.ftl" "START SCRIPT"

# Take the output of the job and write to our standard output
#
OUTPUT="${job.gridJobDirectory}/${sgeJobStdOutFileName}"
if [[ -f "$OUTPUT" ]]; then
    cat "$OUTPUT"
fi

# Take the errors of the job and write to our standard error
#
ERROR="${job.gridJobDirectory}/${sgeJobStdErrFileName}"
if [[ -f "$ERROR" ]]; then
    cat "$ERROR" 1>&2
fi

logMetrics "${job.getJobId()}" "common/getoutput.ftl" "END SCRIPT"
