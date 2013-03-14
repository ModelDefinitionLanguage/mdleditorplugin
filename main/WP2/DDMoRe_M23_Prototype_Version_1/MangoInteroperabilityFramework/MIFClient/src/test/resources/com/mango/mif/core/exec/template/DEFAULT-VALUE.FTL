<#assign finalExpression = defaultValue>

${finalExpression}
<#if emptyExpression = "">
    <#assign finalExpression = "not empty">
</#if>
${finalExpression}
<#if !(nullExpression)??>
    <#assign finalExpression = "not null">
</#if>
${finalExpression}
<#if (defaultValue)?? && defaultValue != "">
    <#assign finalExpression = defaultValue>
</#if>
${finalExpression}
<#if defaultValue.contains("Value") >
${finalExpression}
</#if>
