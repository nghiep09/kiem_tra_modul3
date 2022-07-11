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

<form action="/staff?action=edit" method="post">
    <fieldset>
        <legend>Staff information</legend>
        <table>
            <tr>
                <td>Id:</td>
                <td><input readonly type="text" name="id" id="id" value="${staff.id}"> </td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name" value="${staff.name}"></td>
            </tr>
            <tr>
                <td>Birth:</td>
                <td><input type="date" name="birth" id="Birth" value="${staff.dateOfBirth}"></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" id="address" value="${staff.address}"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone" id="phone" value="${staff.phoneNumber}"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" id="email" value="${staff.email}"></td>
            </tr>
            <tr>
                <td>Department</td>
                <td><select name="class" id="class" value="${staff.getDepartment().name}" >
                    <c:forEach var="c" items="${department}">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Edit customer"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>