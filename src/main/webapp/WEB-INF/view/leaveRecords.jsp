<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Leave Records</title>
</head>

<body>
<h1>Leave Records</h1><br>
<h3>${message}</h3>
<a href = "${pageContext.request.contextPath}/performGetEmployeeForLeave">Add Leave Record</a><br><br>
<a href = "${pageContext.request.contextPath}/performEditLeaveRecord">Update Leave Record</a><br><br>
<a href = "${pageContext.request.contextPath}/performGetLeaveRecord">Show Leave Records</a><br><br>
<a href = "${pageContext.request.contextPath}/performDeleteLeaveRecord">Delete Leave Record</a><br><br>
<a href = "${pageContext.request.contextPath}/index">Back to Main Menu</a>
</body>
</html>