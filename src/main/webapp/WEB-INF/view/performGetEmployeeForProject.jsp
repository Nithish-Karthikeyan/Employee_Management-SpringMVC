<%@ page import="com.ideas2it.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Project</title>
</head>

<body>
<h1>Employee Details</h1>
<h3>Enter the Employee Id</h3>
<form action="getEmployeeForProject" method="post">
    <input type = "text" id = "employeeId" name = "employeeId"><br><br>
    <input type = "submit" value = "Submit">
</form>
<a href = "projects.jsp">Back</a><br><br>
<p>${employee}</p>
</body>
</html>