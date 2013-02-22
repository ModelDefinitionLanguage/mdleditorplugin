#!${SHELL}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/qacct.ftl" ${job.getJobId()}

logMetrics "${job.getJobId()}" "common/qacct.ftl" "START SCRIPT"

set -o pipefail 
${MANGO_SGE_QACCT} -j ${job.getSGEJobID()} | tee "${job.getGridJobDirectoryMifHiddenDir()}/${sgeJobFinalStatusFileName}"
set +o pipefail 

logMetrics "${job.getJobId()}" "common/qacct.ftl" "END SCRIPT"

