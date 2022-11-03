<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Employee Management</title>
    </head>

    <body>
        <h1>Employee Details</h1><br>
        <h3>${message}</h3>
        <a href = "${pageContext.request.contextPath}/performAddEmployee">Add employee</a><br><br>
        <a href = "${pageContext.request.contextPath}/performUpdateEmployee">Update employee</a><br><br>
        <a href = "${pageContext.request.contextPath}/performGetEmployee">Read employee</a><br><br>
        <a href = "${pageContext.request.contextPath}/performDeleteEmployee">Delete employee</a><br><br>
        <a href = "index.jsp">Back to Main Menu</a>
    </body>
</html>