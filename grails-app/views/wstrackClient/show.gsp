
<%@ page import="umd.edu.lib.wstrack.WstrackClient" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wstrackClient.label', default: 'WstrackClient')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-wstrackClient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-wstrackClient" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list wstrackClient">
			
				<g:if test="${wstrackClientInstance?.ip}">
				<li class="fieldcontain">
					<span id="ip-label" class="property-label"><g:message code="wstrackClient.ip.label" default="Ip" /></span>
					
						<span class="property-value" aria-labelledby="ip-label"><g:fieldValue bean="${wstrackClientInstance}" field="ip"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wstrackClientInstance?.guestFlag}">
				<li class="fieldcontain">
					<span id="guestFlag-label" class="property-label"><g:message code="wstrackClient.guestFlag.label" default="Guest Flag" /></span>
					
						<span class="property-value" aria-labelledby="guestFlag-label"><g:formatBoolean boolean="${wstrackClientInstance?.guestFlag}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wstrackClientInstance?.hostName}">
				<li class="fieldcontain">
					<span id="hostName-label" class="property-label"><g:message code="wstrackClient.hostName.label" default="Host Name" /></span>
					
						<span class="property-value" aria-labelledby="hostName-label"><g:fieldValue bean="${wstrackClientInstance}" field="hostName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wstrackClientInstance?.os}">
				<li class="fieldcontain">
					<span id="os-label" class="property-label"><g:message code="wstrackClient.os.label" default="Os" /></span>
					
						<span class="property-value" aria-labelledby="os-label"><g:fieldValue bean="${wstrackClientInstance}" field="os"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wstrackClientInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="wstrackClient.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${wstrackClientInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wstrackClientInstance?.userHash}">
				<li class="fieldcontain">
					<span id="userHash-label" class="property-label"><g:message code="wstrackClient.userHash.label" default="User Hash" /></span>
					
						<span class="property-value" aria-labelledby="userHash-label"><g:fieldValue bean="${wstrackClientInstance}" field="userHash"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${wstrackClientInstance?.id}" />
					<g:link class="edit" action="edit" id="${wstrackClientInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
