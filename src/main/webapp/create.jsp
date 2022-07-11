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
<h1>Create new customer</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/staff">Back to Staff list</a>
</p>

<form action="/staff?action=create" method="post">
    <fieldset>
        <legend>Staff information</legend>
        <table>
            <%--            <tr>--%>
            <%--                <td>Id:</td>--%>
            <%--                <td><input type="text" name="id" id="id"></td>--%>
            <%--            </tr>--%>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Birth:</td>
                <td><input type="date" name="birth" id="Birth"></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" id="address"></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="text" name="phone" id="phone"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td>Department:</td>
                <td><select name="class" id="class">
                    <c:forEach var="c" items="${department}">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create customer"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>