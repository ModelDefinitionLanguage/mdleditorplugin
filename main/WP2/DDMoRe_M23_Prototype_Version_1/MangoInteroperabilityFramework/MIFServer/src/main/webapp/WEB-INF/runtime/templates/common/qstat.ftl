#!${SHELL}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/qstat.ftl" ${job.getJobId()}

logMetrics "${job.getJobId()}" "common/qstat.ftl" "START SCRIPT"
${MANGO_SGE_QSTAT} -j ${job.getSGEJobID()}
logMetrics "${job.getJobId()}" "common/qstat.ftl" "END SCRIPT"
