#!${SHELL}

${SCRIPT_PREAMBLE!}

export PATH=${MANGO_UTILS}:$PATH

/bin/echo -- <#list directoriesToCreate as directoryToCreate>-c "${directoryToCreate}"</#list> <#if directoryToCopy??>-C ${directoryToCopy.getFirst()} ${directoryToCopy.getSecond()}</#if> <#list filesToCopy as fileToCopy>-Z "${fileToCopy.getFirst()}" "${fileToCopy.getSecond()}"</#list>
