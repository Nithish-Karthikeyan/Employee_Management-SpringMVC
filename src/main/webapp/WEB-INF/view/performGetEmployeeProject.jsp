<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Project</title>
</head>
<body>
<form action="getEmployeeForProjectUpdate" method="post">
    <label for="employeeId">Enter the Employee ID:</label>
    <input type = "text" id = "employeeId" name = "employeeId"><br><br>
    <input type = "submit" value = "Submit">
</form>
</body>
</html>
