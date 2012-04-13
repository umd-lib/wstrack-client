<%@ page import="umd.edu.lib.wstrack.WstrackClient" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wstrackClient.label', default: 'WstrackClient')}" />
		<title><g:message code="default.track.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#track-wstrackClient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/raghav')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="track"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="track-wstrackClient" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${wstrackClientInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${wstrackClientInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="track" class="save" value="${message(code: 'default.button.track.label', default: 'track')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
