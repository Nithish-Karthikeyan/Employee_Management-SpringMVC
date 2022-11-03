<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Leave Record</title>
    </head>

    <body>
        <p>Employee ID:</p>
        <input type = "text" name ="employeeId" value = "${employee.employeeId}" readonly><br><br>
        <p>${leaveRecords}</p>
        <form action ="getLeaveId" method="get">
            <input type = "hidden" name ="employeeId" value = "${employee.employeeId}"><br><br>
            <label for ="leaveId" required="required">Enter the leave ID for the record which you want to edit</label><br><br>
            <input type="number" id="leaveId" name="leaveId" min="1" required="required"/>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
