<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Read Employee Details</title>
    </head>

    <body>
    <h2>Employee Details</h2>
        <form action ="readEmployee" method="get">
        <a href ="${pageContext.request.contextPath}/readEmployee">Get All Employee Details</a><br><br>
        <a href ="performReadEmployeeById.jsp">Get Employee Details by ID</a><br><br>
        <a href ="employee.jsp">Back to Menu</a>
        </form>
    </body>
</html>
