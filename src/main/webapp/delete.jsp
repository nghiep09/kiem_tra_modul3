<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new customer</title>
    <style>
        .message {
            color: green;
        }
    </style>
</head>
<body>
<h1>Edit new customer</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/staff">Back to Staff list</a>
</p>

<form action="/staff?action=delete" method="post">
    <fieldset>
        <legend>Staff information</legend>
        <table>
            <tr>
                <td>Id:</td>
                <td><input readonly type="text" name="id" id="id" value="${staff.id}"> </td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input readonly type="text" name="name" id="name" value="${staff.name}"></td>
            </tr>
            <tr>
                <td>Birth:</td>
                <td><input readonly type="date" name="birth" id="Birth" value="${staff.dateOfBirth}"></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input readonly type="text" name="address" id="address" value="${staff.address}"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input readonly type="text" name="phone" id="phone" value="${staff.phoneNumber}"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input readonly type="text" name="email" id="email" value="${staff.email}"></td>
            </tr>
            <tr>
                <td>Department</td>
                <td><input readonly name="class" id="class" value="${staff.getDepartment().name}" >
                </input></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="delete"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>