<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Projects</title>
</head>

<body>
<h2>Employee Projects</h2>
<form action ="getProjects" method="post">
    <a href ="${pageContext.request.contextPath}/getProjects">Get All Projects</a><br><br>
    <a href ="${pageContext.request.contextPath}/performGetProjectByEmployeeId">Get Project Details of an Employee</a><br><br>
    <a href ="projects.jsp">Back to Menu</a>
</form>
</body>
</html>