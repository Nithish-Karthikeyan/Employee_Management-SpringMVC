<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Projects</title>
    </head>

    <body>
        <h1>Projects</h1>
        <h3>Enter the Employee Id</h3>
        <form action="getProjectsByEmployeeId" method="get">
            <label for = "employeeId"></label>
            <input type = "text" id = "employeeId" name = "employeeId"><br><br>
            <input type = "submit" value = "Submit">
        </form>
        <a href = "performGetProject.jsp">Back</a><br><br>
    </body>
</html>