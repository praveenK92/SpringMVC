<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>

<html>
<head>
<title>Employee</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<style>
	span.error {
		color: red;
	}
	div.errors {
		background-color: #ffcccc;
		border: 2px solid red;
	}
	label.error {
		color: red;
	}
	input.error {
		background-color: #ffcccc;
	}
</style>
<body>
	
	<h1>Register</h1>
	<sf:form method="POST" modelAttribute="employee">
		
		<sf:errors path="*" element="div" cssClass="errors" />
		
		<s:message code="emp.id"/><sf:input path="id" type="number"/><br />
			<%-- <sf:errors path="id" cssClass="error"/><br/> --%>
		
		<%-- <sf:label path="name" cssErrorClass="error" ></sf:label> --%>
		<s:message code="emp.name"/><sf:input type="text" path="name" /><br />
			<%-- <sf:errors path="name" cssClass="error"/><br/> --%>
		
		<s:message code="emp.salary"/><sf:input type="number" path="salary" /><br />
			<%-- <sf:errors path="salary" cssClass="error"/><br/> --%>
		
		<input type="submit" value="Register" />
	
	</sf:form>
</body>

</html>