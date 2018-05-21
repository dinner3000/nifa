<#setting number_format="0">
<#list list as item>
${item.uniqueid!''}|${item.usertype!''}|${item.userid!''}|${item.certtype!''}|${item.certid!''}|${item.industrialtype!''}|${item.city!''}|${item.guildsec!''}|${(item.businesssum!0)?string('0.0000')}|${item.investstatus!''}
</#list>
