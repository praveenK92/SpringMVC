<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:message code="emp.stuffs" /></title>
</head>
<body>
	<s:message code="emp.stuffs" />
	<br>
	<c:forEach items="${empList}" var="emp1">
		<%-- <li id="employee">
			<div class="employeeRa">
				<c:out value="${emp1.id}" />
				<c:out value="${emp1.name}" />
				<c:out value="${emp1.salary}" />
			</div>
		</li> --%>
		<li>${emp1.id}</li>
		<li>${emp1.name}</li>
		<li>${emp1.salary}</li>

	</c:forEach>
</body>
</html>