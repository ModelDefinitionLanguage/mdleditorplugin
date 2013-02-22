#!${SHELL}

<#--
  Prepare Handler Freemarker Template

  SHELL                   the preferred execution shell (full path)
  SUBMIT_HOST_PREAMBLE    any series of variable assignments and commands that you want executed before the real work starts
  REQUEST_ATTRIBUTE_BLOCK values from the request attributes which have come from navigator's execution profile
  MANGO_UTILS             the directory containing the mango utility shell scripts
  CONNECTOR_UTILS         the directory containing the publishHandler.sh script, which is connector specific
  sgeSharedLocation       the grid shared directory we are copying to
  job                     the current job
-->

export PATH="${NONMEMSGE_CONNECTOR_UTILS}:${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "nonmemsgeconnector/validateNonmemOutputs.ftl" ${job.getJobId()}
logMetrics "${job.getJobId()}" "nonmemsgeconnector/validateNonmemOutput.ftl" "START SCRIPT"
source nonmem_functions.fn.sh

set +e
set +o pipefail

mifCtlFileAbs="${job.getGridJobDirectory()}/${job.executionRequest.executionFile}"
mifCtlFileWd="${job.getGridJobDirectory()}/$(dirname '${job.executionRequest.executionFile}')"

mifGridOutputFileName="${job.getSGEJobOutputStreamFile()}"
mifGridErrorFileName="${job.getSGEJobErrorStreamFile()}"

mifNonmemFatalErrorsCount=$(countNumberOfMessages "$mifGridErrorFileName" "Fatal Error:")
mifGridOutputFileContent=$(getFileContent "$mifGridOutputFileName")
mifGridErrorFileContent=$(getFileContent "$mifGridErrorFileName")

if grep "ERROR WAS FOUND" $mifGridOutputFileName > /dev/null; then
    logErrorAndExit 125 "Errors found in NONMEM output.\n\n$mifGridOutputFileContent"
fi

if (( $mifNonmemFatalErrorsCount >= 1 )); then
    logErrorAndExit 126 "Fatal error found in NONMEM output.\n\n$mifGridErrorFileContent"
fi

mifLstFile="$mifCtlFileWd/${job.getRunLstFileName()}"
if [[ ! -f $mifLstFile ]]; then
    logErrorAndExit 127 "Required *.lst output file \"$mifLstFile\" was not found.  Were there syntax errors in the job?"
fi

isSIM=0
if sed -e 's/;.*//' $mifCtlFileAbs | grep "\$SIM" > /dev/null; then
    isSIM=1
fi
mifObjValueCount=$(countNumberOfMessages "$mifLstFile" "#OBJV")
    
if (( isSIM == 0 && mifObjValueCount == 0 )); then
    mifLstFileContent=$(getFileContent "$mifLstFile")
    logErrorAndExit 128 "Required #OBJV in $mifLstFile output file was not found.\n\n$mifLstFileContent"
fi 

logMetrics "${job.getJobId()}" "nonmemsgeconnector/validateNonmemOutput.ftl" "END SCRIPT"

exit 0
