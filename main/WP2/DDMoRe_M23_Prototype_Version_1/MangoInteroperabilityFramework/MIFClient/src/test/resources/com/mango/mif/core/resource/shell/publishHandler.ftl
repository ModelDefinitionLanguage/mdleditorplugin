#!/bin/sh

<#--
  Publish Handler Freemarker Template

  SUBMIT_HOST_PREAMBLE      any series of variable assignments and commands that you want executed before the real work starts
  MANGO_UTILS          the directory containing the mango utility shell scripts
  CONNECTOR_UTILS      the directory containing the publishHandler.sh script, which is connector specific
  directoriesToCreate  a list of absolute paths of directories the script must create
  directoriesToCopy    a list of pairs of absolute paths of directories the script will recursively copy
  filesToCopy          a list of pairs of absolute paths of files the script will copy
  specialFilesToCopy   another list of pairs of absolute paths of files the script will copy (deprecated)
-->

# Bail on the first error
set -e

${SUBMIT_HOST_PREAMBLE!}

export PATH="${CONNECTOR_UTILS}:${MANGO_UTILS}:$PATH"

publishHandler.sh \
    <#if directoriesToCreate??><#list directoriesToCreate as dir>-d "${dir}" </#list></#if>\
    <#if directoriesToCopy??>-r ${directoriesToCopy.getFirst()} ${directoriesToCopy.getSecond()}</#if>\
    <#list filesToCopy as pair>-c "${pair.getFirst()}" "${pair.getSecond()}" </#list>
    <#if specialFilesToCopy??><#list specialFilesToCopy as pair>-c "${pair.getFirst()}" "${pair.getSecond()}" </#list></#if>
