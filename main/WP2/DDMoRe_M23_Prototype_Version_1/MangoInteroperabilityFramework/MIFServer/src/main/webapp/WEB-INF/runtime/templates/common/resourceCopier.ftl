#!${SHELL}

<#--
  Resource Copier Freemarker Template

  Warning!  Use of resource copier is rather arcane, if filesToCopy is specified, everything else is ignored

  SHELL                   the preferred executing shell, absolute path
  SUBMIT_HOST_PREAMBLE    any series of variable assignments and commands that you want executed before the real work starts
  MANGO_UTILS             the directory containing the mango utility shell scripts
  CONNECTOR_UTILS         the directory containing the publishHandler.sh script, which is connector specific
  jobId                   the optional job id (for logging purposes)
  destinationDirectory    the target directory
  sourceDirectory         the source directory
  ignorePattern           regexp telling us what entries to ignore when copying
  filesToCopy             explicit list of files to copy which overrides everything else
-->

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

SGE_INITIALISATION_SCRIPT_PATH=${SGE_INITIALISATION_SCRIPT_PATH!}; export SGE_INITIALISATION_SCRIPT_PATH
# REQUEST_ATTRIBUTE_BLOCK
${REQUEST_ATTRIBUTE_BLOCK}
# SUBMIT_HOST_PREAMBLE
${SUBMIT_HOST_PREAMBLE}

source "mango_initialise.sh" "common/resourceCopier.ftl" ${jobId!}

logMetrics "${jobId!}" "common/resourceCopier.ftl" "START SCRIPT"

<#if filesToCopy??>
  resourceCopier.sh \
     <#list filesToCopy as pair>-c "${pair.getFirst()}" "${pair.getSecond()}" </#list>
<#else>
  resourceCopier.sh \
     <#if destinationDirectory??>-d "${destinationDirectory}"</#if>\
     <#if sourceDirectory??>-s "${sourceDirectory}"</#if>\
     <#if ignorePattern??>-i "${ignorePattern}"</#if>\
     <#if filesListing??>-l "${filesListing}"</#if>
</#if>

logMetrics "${jobId!}" "common/resourceCopier.ftl" "END SCRIPT"

