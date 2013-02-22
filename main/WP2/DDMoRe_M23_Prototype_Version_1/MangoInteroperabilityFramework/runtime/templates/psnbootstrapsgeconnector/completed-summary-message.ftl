#!${SHELL}

# Completed Summary Message Template: This gathers and assembles text prettily for completed PSN runs.

<#--
  SHELL                     the preferred execution shell (full path)
  SUBMIT_HOST_PREAMBLE      any series of variable assignments and commands that you want executed before the real work starts
  REQUEST_ATTRIBUTE_BLOCK   values from the request attributes which have come from the navigator execution profile
  MANGO_UTILS               the directory containing the mango utility shell scripts
  NONMEMSGE_CONNECTOR_UTILS the directory containing utilities specific to NONMEM/PsN
  job                       the current job
-->

export PATH="${PSN_SGECONNECTOR_UTILS}:${NONMEMSGE_CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source mango_initialise.sh "psnbootstrapsgeconnector/completed-summary-message.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "psnbootstrapsgeconnector/completed-summary-message.ftl" "START SCRIPT"

source nonmem_functions.fn.sh

createSummaryMessageForCompletedNONMEMRun "${job.getPsNOutputDir()}/orig_modelfit_dir1/NM_run1" "psn.mod" "psn.lst"

logMetrics "${job.getJobId()}" "psnbootstrapsgeconnector/completed-summary-message.ftl" "END SCRIPT"
