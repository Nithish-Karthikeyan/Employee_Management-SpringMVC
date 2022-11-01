<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Add an employee</title>
    </head>

    <body>
        <h2>Add Employee</h2><br>
        <h3>Fill the details</h3><br>

        <form:form action = "addEmployee" method = "post" modelAttribute = "employee">
            <form:label path="employeeName">Name</form:label><br>
            <form:input type = "text" path="employeeName" required = "required" minlength="3" maxlength="40" pattern ="([a-zA-Z]{1,10}[\s.]?([a-zA-Z]{1,20}[\s.]?)+)"/><br><br>

            <form:label path="employeeGender" required = "required">Gender</form:label><br>
            <form:select path="employeeGender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </form:select><br><br>

            <form:label path="employeeType" required = "required">Employee Type</form:label><br>
            <form:select path="employeeType">
                <option value="Trainer">Trainer</option>
                <option value="Trainee">Trainee</option>
            </form:select><br><br>

            <form:label path="dateOfBirth">Date Of Birth</form:label><br>
            <form:input path="dateOfBirth" type="date" required = "required"/><br><br>

            <form:label path = "mobileNumber">Mobile Number</form:label><br>
            <form:input path="mobileNumber" type = "text" required = "required" minlength="10" maxlength="10" pattern = "^[6789]{1}[0-9]{9}"/><br><br>

            <form:label path = "emailId">Email Id</form:label><br>
            <form:input path="emailId" type = "email" required = "required" pattern = "(^([a-z0-9_.-]{3,})+@([a-z0-9_.-]+)+[a-z.]{2,}$)"/><br><br>

            <form:label path = "designation">Designation</form:label><br>
            <form:input path = "designation" type = "text" required = "required" pattern = "([a-zA-Z]{1,10}[\s.]?([a-zA-Z]{1,20}[\s.]?)+)"/><br><br>

            <input type = "submit" value = "Submit"/>
        </form:form>

        <h3>${status}</h3><br><br>
        <a href = "employee.jsp">Back to Menu</a>
    </body>
</html>
