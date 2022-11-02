<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Leave Records</title>
    </head>

    <body>
        <h1>Leave Records</h1>
        <h3>Enter the Employee Id</h3>
        <form action="readLeaveRecordByEmployeeId" method="get">
            <label path = "employeeId">Enter the employee ID:</label>
            <input type = "text" name = "employeeId"/><br><br>
            <input type ="submit" value = "Submit">
            <a href = "performGetLeaveRecord.jsp">Back</a><br><br>
            <p>${leave}</p><br><br>
        </form>
    </body>
</html>