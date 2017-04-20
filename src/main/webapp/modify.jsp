<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2017/4/19
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>modify</title>
    <style>
        .form table {
            border: 1px solid #d8d8d8;
            border-radius: 2px;
            color: #333;
            border-collapse: collapse;
            font-size: 12px;
        }

        .form table thead {
            background: #efefef;
            font-weight: bolder;
        }

        .form table tbody td {
            font-weight: normal;
        }

        .form table tbody tr:hover {
            background: #f5f5f5;
        }

        .form table th, .table td {
            padding: 5px 8px;
        }
    </style>
</head>
<body>
<form class="form" action="${pageContext.request.contextPath}/modify" method="post">
    <table align="center" border="1">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>birthday</th>
            <th>description</th>
            <th>avgScore</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input name="id" type="text" value="${student.id}"></td>
            <td><input name="name" type="text" value="${student.name}"></td>
            <td>
                <input name="birthday" type="text" value="<fmt:formatDate value='${student.birthday}' pattern='yyyy-MM-dd'/>">
            </td>
            <td><input name="description" type="text" value="${student.description}"></td>
            <td><input name="avgScore" type="text" value="${student.avgScore}"></td>
            <td><input type="submit"></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
