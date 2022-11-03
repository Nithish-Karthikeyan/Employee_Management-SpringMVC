<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Leave Record</title>
</head>

<body>
<h2>Add Leave Record</h2><br>
<h3>Fill the details</h3><br>

<form:form action = "addLeaveRecord" method = "post" modelAttribute="leaveRecord">
    <p>Remaining leaves: ${leaveCount} </p>
    <p>Employee ID:</p>
    <form:input path="employeeId" name="employeeId" value="${employee.employeeId}" readonly="true"/><br><br>

    <form:label path="fromDate">From Date:</form:label><br>
    <form:input type="date" path="fromDate" required = "required"/><br><br>

    <form:label path="toDate">To Date:</form:label><br>
    <form:input type="date" path="toDate" required = "required"/><br><br>

    <form:label path="leaveType" required = "required">Leave Type</form:label><br>
    <form:select path="leaveType" id="leaveType">
        <option value="Casual Leave">Casual Leave</option>
        <option value="Medical Leave">Medical Leave</option>
        <option value="Sick Leave">Sick Leave</option>
    </form:select><br><br>

    <input type = "submit" value = "Submit">

</form:form>
<h3>${status}</h3><br><br>
<a href = "performGetEmployeeForLeave.jsp">Back to Menu</a>
</body>
</html>