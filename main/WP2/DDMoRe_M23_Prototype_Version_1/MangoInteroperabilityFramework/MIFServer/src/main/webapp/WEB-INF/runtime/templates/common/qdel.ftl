#!${SHELL}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/qdel.ftl" ${job.getJobId()}

logMetrics "${job.getJobId()}" "common/qdel.ftl" "START SCRIPT"
${MANGO_SGE_QDEL} ${job.getSGEJobID()}
logMetrics "${job.getJobId()}" "common/qdel.ftl" "END SCRIPT"
