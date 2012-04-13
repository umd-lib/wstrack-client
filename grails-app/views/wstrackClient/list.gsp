
<%@ page import="umd.edu.lib.wstrack.WstrackClient" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wstrackClient.label', default: 'WstrackClient')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-wstrackClient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-wstrackClient" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="ip" title="${message(code: 'wstrackClient.ip.label', default: 'Ip')}" />
					
						<g:sortableColumn property="guestFlag" title="${message(code: 'wstrackClient.guestFlag.label', default: 'Guest Flag')}" />
					
						<g:sortableColumn property="hostName" title="${message(code: 'wstrackClient.hostName.label', default: 'Host Name')}" />
					
						<g:sortableColumn property="os" title="${message(code: 'wstrackClient.os.label', default: 'Os')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'wstrackClient.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="userHash" title="${message(code: 'wstrackClient.userHash.label', default: 'User Hash')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${wstrackClientInstanceList}" status="i" var="wstrackClientInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${wstrackClientInstance.id}">${fieldValue(bean: wstrackClientInstance, field: "ip")}</g:link></td>
					
						<td><g:formatBoolean boolean="${wstrackClientInstance.guestFlag}" /></td>
					
						<td>${fieldValue(bean: wstrackClientInstance, field: "hostName")}</td>
					
						<td>${fieldValue(bean: wstrackClientInstance, field: "os")}</td>
					
						<td>${fieldValue(bean: wstrackClientInstance, field: "status")}</td>
					
						<td>${fieldValue(bean: wstrackClientInstance, field: "userHash")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${wstrackClientInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
