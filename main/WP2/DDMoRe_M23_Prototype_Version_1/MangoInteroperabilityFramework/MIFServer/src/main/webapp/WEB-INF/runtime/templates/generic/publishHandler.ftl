#!${SHELL}

<#--
  Publish Handler Freemarker Template

  SUBMIT_HOST_PREAMBLE    any series of variable assignments and commands that you want executed before the real work starts
  REQUEST_ATTRIBUTE_BLOCK values from the execution request attributes from the execution profile in Navigator
  MANGO_UTILS             the directory containing the mango utility shell scripts
  CONNECTOR_UTILS         the directory containing the publishHandler.sh script, which is connector specific
  navigatorJobId          a variable containing the job id as populated by navigator
  directoriesToCreate     a list of absolute paths of directories the script must create
  directoriesToCopy       a list of pairs of absolute paths of directories the script will recursively copy
  filesToCopy             a list of pairs of absolute paths of files the script will copy
  specialFilesToCopy      another list of pairs of absolute paths of files the script will copy (deprecated)
-->
export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "generic/publishHandler.ftl" ${navigatorJobId!}
logMetrics "${navigatorJobId!}" "generic/publishHandler.ftl" "START SCRIPT"

publishHandler.sh \
    <#if directoriesToCreate??><#list directoriesToCreate as dir>-d "${dir}" </#list></#if>\
    <#if directoriesToCopy??>-r ${directoriesToCopy.getFirst()} ${directoriesToCopy.getSecond()}</#if>\
    <#list filesToCopy as pair>-c "${pair.getFirst()}" "${pair.getSecond()}" </#list>
    <#if specialFilesToCopy??><#list specialFilesToCopy as pair>-c "${pair.getFirst()}" "${pair.getSecond()}" </#list></#if>

logMetrics "${navigatorJobId!}" "generic/publishHandler.ftl" "END SCRIPT"
