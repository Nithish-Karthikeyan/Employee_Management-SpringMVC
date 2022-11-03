<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Project</title>
    </head>

    <body>
        <h2>Assign Employee For Project</h2>
        <form action ="assignEmployee" method = "post">
            <input type ="hidden" name ="employeeId" value="${employee.employeeId}">
            <p>${projects}</p>
            <label for ="projectId" required="required">Enter the Project ID:</label>
            <input type = "number" id ="projectId" name ="projectId">
            <input type ="submit" value = "Submit">
        </form>
     <a href = "getEmployeeForProject.jsp">Back</a>
    </body>
</html>
