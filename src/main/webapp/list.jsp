<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: king
  Date: 2017/4/18
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
    <style>
        a:link {
            text-decoration: none;
        }

        a:visited {
            text-decoration: none;
        }

        a:hover {
            text-decoration: none;
        }

        a:active {
            text-decoration: none;
        }

        .table {
            border: 1px solid #d8d8d8;
            border-radius: 2px;
            color: #333;
            border-collapse: collapse;
            font-size: 12px;
        }

        .table thead {
            background: #efefef;
            font-weight: bolder;
        }

        .table tbody td {
            font-weight: normal;
        }

        .table tbody tr:hover {
            background: #f5f5f5;
        }

        .table th, .table td {
            padding: 5px 8px;
        }
    </style>
</head>

<body>
<table class="table" border="1" align="center">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>birthday</th>
        <th>description</th>
        <th>avgScore</th>
        <th>modify</th>
        <th>delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageBean.studentList}" var="s">
        <tr>
            <th>${s.id}</th>
            <th>${s.name}</th>
            <th><fmt:formatDate value='${s.birthday}' pattern='yyyy-MM-dd'/></th>
            <th>${s.description}</th>
            <th>${s.avgScore}</th>
            <th>
                <a href="${pageContext.request.contextPath}/toModify?key=${s.name}">[MODIFY]</a>
            </th>
            <th>
                <a href="${pageContext.request.contextPath}/delete?key=${s.name}">[DELETE]</a>
            </th>
        </tr>
    </c:forEach>

    <tr>
        <td><a href="${pageContext.request.contextPath}/modify.jsp">[添加]</a></td>
    </tr>
    </tbody>
</table>

<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td align="right">
        <span>
        	<a href="${pageContext.request.contextPath}/list">[首页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/pageUp?currentPage=${pageBean.currentPage}">[上一页]</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/pageDown?currentPage=${pageBean.currentPage}">[下一页]</a>&nbsp;&nbsp;
        </span>
        </td>
    </tr>
</table>
</body>

</html>
