<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Leave Record</title>
    </head>

    <body>
        <h2>Update Leave Record</h2><br>
        <h3>Fill the details</h3><br>

        <form:form action = "updateLeaveRecord" method = "post">
            <form:input type="hidden" path="employee" value="${leaveRecord.employee}"/>

            <form:input type ="text" path ="leaveId" value = "${leaveRecord.leaveId}" readonly="true" /><br>
            <form:label path="fromDate">From Date</form:label><br>
            <form:input type="date" path="fromDate" required = "required" value = "${leaveRecord.fromDate}"/><br><br>

            <form:label path="toDate">TO Date</form:label><br>
            <form:input type="date" path="toDate" required = "required" value = "${leaveRecord.toDate}"/><br><br>

            <form:label path="leaveType" required = "required">Leave Type</form:label><br>
            <form:select path="leaveType" value = "${leaveRecord.leaveType}">
                <option value="Casual Leave">Casual Leave</option>
                <option value="Medical Leave">Medical Leave</option>
                <option value="Sick Leave">Sick Leave</option>
            </form:select><br><br>

            <input type = "submit" value = "Submit">
        </form:form>
        <h3>${status}</h3><br><br>
        <a href = "leaveRecords.jsp">Back to Menu</a>
    </body>
</html>