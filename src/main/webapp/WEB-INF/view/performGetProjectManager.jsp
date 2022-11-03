<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Project</title>
</head>
<body>
<h3>Enter Project Manager Id</h3>
<form:form action="getProjectManager" method="get" modelAttribute = "employee">
    <form:input type="hidden" path="employeeId" name = "employeeId" value = "${employee.employeeId}"/>
    <input type = "text" name = "projectManagerId"/><br><br>
    <input type = "submit" value = "Submit">
</form:form>
<a href = "projects.jsp">Back</a><br><br>
</body>
</html>
