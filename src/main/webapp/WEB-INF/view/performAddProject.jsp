<%@ page import="com.ideas2it.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Project</title>
</head>

<body>
<h2>Project</h2><br>
<h3>Fill the details</h3><br>

<form:form action = "addProject" method = "post" modelAttribute = "project">
    
    <form:label path = "employeeId">Employee ID :</form:label>
    <form:input type= "text" name="employeeId" path="employeeId" value ="${employee.employeeId}" readonly="true" /><br><br>

    <form:label path="projectManagerName">Project Manager Name:</form:label><br>
    <form:input type="text" path="projectManagerName" value="${projectManager.employeeName}" readonly = "readonly"/><br><br>

    <form:label path="projectName">Project Name:</form:label><br>
    <form:input type="text" path="projectName" required = "required" pattern = "([a-zA-Z0-9]{1,10}[\s.]?([a-zA-Z0-9]{1,20}[\s.]?)+)"/><br><br>

    <form:label path="clientName">Client Name:</form:label><br>
    <form:input type="text" path="clientName" required = "required" pattern = "([a-zA-Z0-9]{1,10}[\s.]?([a-zA-Z0-9]{1,20}[\s.]?)+)"/><br><br>

    <form:label path="startDate">Project Start Date:</form:label><br>
    <form:input type="date" path="startDate" required = "required"/><br><br>

    <input type = "submit" value = "Submit">
</form:form>
<a href = "projects.jsp">Back to Menu</a>
</body>
</html>