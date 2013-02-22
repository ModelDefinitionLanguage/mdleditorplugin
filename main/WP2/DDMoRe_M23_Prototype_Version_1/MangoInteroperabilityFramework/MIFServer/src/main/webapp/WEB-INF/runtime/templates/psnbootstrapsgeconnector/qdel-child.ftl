#!${SHELL}

export PATH="${PSN_SGECONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/qdel-child.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "common/qdel-child.ftl" "START SCRIPT"
source "psn_functions.fn.sh"

BOOTSTRAP_ROOT="${job.getGridJobCurrentWorkingDirectory()}"

finalStatus=0
finalResult=""
set +e
<#list sgeJobIDs as jobId> 
qdelResult=$(${MANGO_SGE_QDEL} ${jobId})
status=$?
if (( status == 0 )); then
    find "$BOOTSTRAP_ROOT" -noleaf -type f -name "JobId" -exec grep -l ${jobId} {} \; | while read file; do cp "$file" "$(dirname $file)/$MIF_CANCELLED_JOB_ID_FILE_NAME"; done
else
    finalStatus=$status
fi
finalResult=$(echo -e "$finalResult \n $qdelResult")
</#list>
set -e
echo $finalResult

logMetrics "${job.getJobId()}" "common/qdel-child.ftl" "END SCRIPT"

exit $finalStatus
