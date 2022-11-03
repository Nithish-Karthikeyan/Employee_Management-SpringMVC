<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Employee Management</title>
    </head>

    <body>
        <h1>Employee Management</h1><br>
        <a href = "${pageContext.request.contextPath}/employee">Employee Details</a><br><br>
        <a href = "${pageContext.request.contextPath}/leaveRecords">Leave Records</a><br><br>
        <a href = "${pageContext.request.contextPath}/projects">Project Details</a><br><br>
        <a href = "${pageContext.request.contextPath}/performGetEmployeeForProjectAssign">Assign Employee</a><br><br>
    </body>
</html>
