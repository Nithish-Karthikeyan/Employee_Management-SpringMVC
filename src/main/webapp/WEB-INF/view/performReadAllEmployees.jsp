<%@ page import="com.ideas2it.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Employee Details</title>
    </head>

    <body>
        <h2>Employee Details</h2>
        <h3>${employeeList}</h3>
        <a href = "performGetEmployee.jsp">Back</a><br><br>
    </body>
</html>
