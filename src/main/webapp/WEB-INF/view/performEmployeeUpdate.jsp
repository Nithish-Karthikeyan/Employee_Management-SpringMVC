<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Update Form</title>
    </head>

    <body>
        <p>${status}</p>
        <form:form action = "updateEmployee" method ="post" modelAttribute="employee">
            <h3>Fill the details</h3><br>

            <p>Employee ID :
            <input name = "employeeId" value = "${employee.employeeId}" readonly = "readonly"/>
            <input type="hidden" name = "createdAt" value = "${employee.createdAt}"/><br><br>

            <form:label path = "employeeName">Name</form:label><br>
            <form:input type = "text" path = "employeeName" required = "required" minlength="3" maxlength="40" value = "${employee.employeeName}" pattern = "([a-zA-Z]{1,10}[\s.]?([a-zA-Z]{1,20}[\s.]?)+)"/><br><br>

            <form:label path = "employeeType" required = "required">Employee Type</form:label><br>
            <form:select path="employeeType">
                <option value="Trainer">Trainer</option>
                <option value="Trainee">Trainee</option>
            </form:select><br><br>

            <form:label path="employeeGender" required = "required">Gender</form:label><br>
            <form:select path="employeeGender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </form:select><br><br>

            <form:label path="dateOfBirth">Date Of Birth</form:label><br>
            <form:input type="date" path="dateOfBirth" required = "required" value = "${employee.dateOfBirth}"/><br><br>

            <form:label path = "mobileNumber">Mobile Number</form:label><br>
            <form:input type = "text" path = "mobileNumber" required = "required" minlength="10" maxlength="10" value = "${employee.mobileNumber}" pattern = "^[6789]{1}[0-9]{9}"/><br><br>

            <form:label path = "emailId">Email Id</form:label><br>
            <form:input type = "email" path = "emailId" required = "required" value = "${employee.emailId}" pattern = "(^([a-z0-9_.-]{3,})+@([a-z0-9_.-]+)+[a-z.]{2,}$)"/><br><br>

            <form:label path = "designation">Designation</form:label><br>
            <form:input type = "text" path = "designation" required = "required" value = "${employee.designation}" pattern = "([a-zA-Z]{1,10}[\s.]?([a-zA-Z]{1,20}[\s.]?)+)"/><br><br>

            <input type = "submit" value = "Submit"/>

            <h4>${status}</h4>
            <a href = "performUpdateEmployee.jsp">Back</a>
        </form:form>
    </body>
</html>