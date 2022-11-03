<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Employee Projects</title>
</head>

<body>
<h1>Projects Details</h1><br>
<h2>${message}</h2>
<a href = "${pageContext.request.contextPath}/performGetEmployeeForProject">Add Project</a><br><br>
<a href = "${pageContext.request.contextPath}/performGetProjectId">Update Project</a><br><br>
<a href = "${pageContext.request.contextPath}/performGetProject">Show Projects</a><br><br>
<a href = "index.jsp">Back to Main Menu</a>
</body>
</html>
