<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
<title><s:message code="emp" /></title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1><s:message code="register" /></h1>
	<form method="POST">
		<s:message code="emp.id" /> <input type="number" name="Id" /><br />
		<s:message code="emp.name" /> <input type="text" name="Name" /><br />
		<s:message code="emp.salary" /> <input type="number" name="Salary" /><br />
		<input type="submit" value="Register" />
	</form>
</body>
</html>