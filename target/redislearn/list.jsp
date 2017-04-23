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
    <link rel="stylesheet" href="css/bootstrap.min.css">

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
    <c:forEach items="${pageBean.studentList}" var="s" varStatus="obj">
        <c:if test="${obj.count%2 == '0'}">
            <tr style="background-color: #cccccc;">
                <th>${s.id}</th>
                <th>${s.name}</th>
                <th><fmt:formatDate value='${s.birthday}' pattern='yyyy-MM-dd'/></th>
                <th>${s.description}</th>
                <th>${s.avgScore}</th>
                <th>
                    <a href="${pageContext.request.contextPath}/toModify?key=${s.id}">[修改]</a>
                </th>
                <th>
                    <a href="${pageContext.request.contextPath}/delete?key=${s.id}" style="color: brown">[删除]</a>
                </th>
            </tr>
        </c:if>
        <c:if test="${obj.count%2 != '0'}">
            <tr>
                <th>${s.id}</th>
                <th>${s.name}</th>
                <th><fmt:formatDate value='${s.birthday}' pattern='yyyy-MM-dd'/></th>
                <th>${s.description}</th>
                <th>${s.avgScore}</th>
                <th>
                    <a href="${pageContext.request.contextPath}/toModify?key=${s.id}">[修改]</a>
                </th>
                <th>
                    <a href="${pageContext.request.contextPath}/delete?key=${s.id}" style="color: brown">[删除]</a>
                </th>
            </tr>
        </c:if>
    </c:forEach>

    </tbody>
</table>

<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td align="right">
        <span>
            <ul class="pagination">
                <li><a href="${pageContext.request.contextPath}/modify.jsp">添加</a></li>
                <li><a href="${pageContext.request.contextPath}/list">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/pageDown?currentPage=${pageBean.currentPage-1}">上一页</a></li>
                <li><a href="#">当前第${pageBean.currentPage}页,共${pageBean.totalPage}页</a></li>
                <li><a href="${pageContext.request.contextPath}/pageDown?currentPage=${pageBean.currentPage+1}">下一页</a></li>
                <li><a href="${pageContext.request.contextPath}/pageDown?currentPage=${pageBean.totalPage}">尾页</a></li>
            </ul>
        </span>
        </td>
    </tr>
</table>


<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>

</html>
