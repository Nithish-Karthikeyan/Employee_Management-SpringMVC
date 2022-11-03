<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Project</title>
    </head>

    <body>
        <form action = "getProjectId" method ="get">
            <label for ="projectId" required="required">Enter the Project ID:</label>
            <input type = "number" id ="projectId" name ="projectId">
            <input type ="submit" value = "Submit">
        </form>
    </body>
</html>
