<%@ page import="umd.edu.lib.wstrack.WstrackClient" %>



<div class="fieldcontain ${hasErrors(bean: wstrackClientInstance, field: 'ip', 'error')} required">
	<label for="ip">
		<g:message code="wstrackClient.ip.label" default="Ip" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ip" required="" value="${wstrackClientInstance?.ip}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wstrackClientInstance, field: 'guestFlag', 'error')} ">
	<label for="guestFlag">
		<g:message code="wstrackClient.guestFlag.label" default="Guest Flag" />
		
	</label>
	<g:checkBox name="guestFlag" value="${wstrackClientInstance?.guestFlag}" />
</div>

<div class="fieldcontain ${hasErrors(bean: wstrackClientInstance, field: 'hostName', 'error')} ">
	<label for="hostName">
		<g:message code="wstrackClient.hostName.label" default="Host Name" />
		
	</label>
	<g:textField name="hostName" value="${wstrackClientInstance?.hostName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wstrackClientInstance, field: 'os', 'error')} ">
	<label for="os">
		<g:message code="wstrackClient.os.label" default="Os" />
		
	</label>
	<g:textField name="os" value="${wstrackClientInstance?.os}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wstrackClientInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="wstrackClient.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${wstrackClientInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wstrackClientInstance, field: 'userHash', 'error')} ">
	<label for="userHash">
		<g:message code="wstrackClient.userHash.label" default="User Hash" />
		
	</label>
	<g:textField name="userHash" value="${wstrackClientInstance?.userHash}"/>
</div>

