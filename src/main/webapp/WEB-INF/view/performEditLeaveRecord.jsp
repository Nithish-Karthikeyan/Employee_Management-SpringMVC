<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Leave Records</title>
    </head>

    <body>
        <h1>Leave Records</h1>
        <h3>Enter the Employee Id</h3>
        <form action="getEmployeeForUpdate" method="get">
            <label for = "employeeId"></label>
            <input type = "text" id = "employeeId" name = "employeeId"/><br><br>
            <input type = "submit" value = "Submit"/>
        </form>
        <a href = "leaveRecords.jsp">Back to Main Menu</a><br><br>
    </body>
</html>