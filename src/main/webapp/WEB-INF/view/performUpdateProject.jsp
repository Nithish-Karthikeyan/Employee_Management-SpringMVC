<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Project</title>
</head>

<body>
<h2>Update Project</h2><br>
<h3>Fill the details</h3><br>

<form:form action = "updateProject" method = "post" modelAttribute="project">

    <input type ="hidden" name ="projectId" value="${project.projectId}">
    <label name="projectManagerName">Project Manager Name:</label><br>
    <input type="text"  name="projectManagerName" value="${project.projectManagerName}" readonly = "readonly"/>
    <input type="hidden" name = "createdAt" value = "${project.createdAt}"/><br><br>

    <form:label path="projectName">Project Name:</form:label><br>
    <form:input type="text" path="projectName" value="${project.projectName}" required = "required" pattern = "([a-zA-Z0-9]{1,20}[\s.]?([a-zA-Z0-9]{1,20}[\s.]?)+)"/><br><br>

    <form:label path="clientName">Client Name:</form:label><br>
    <form:input type="text" path="clientName" value="${project.clientName}" required = "required" pattern = "([a-zA-Z]{1,20}[\s.]?([a-zA-Z]{1,20}[\s.]?)+)"/><br><br>

    <form:label path="startDate">Project Start Date:</form:label><br>
    <form:input type="date" path="startDate"  value = "${project.startDate}"  required = "required"/><br><br>

    <input type = "submit" value = "Submit">
</form:form>
<a href = "projects.jsp">Back to Menu</a>
</body>
</html>