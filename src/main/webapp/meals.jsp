<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<style>
    td, th {
        border: 1px solid black;
    }
    table {
        border-collapse: collapse;
    }
    .normal {
        color: green;
    }
    .excess {
        color: red;
    }

</style>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

    <table>
        <tr>
            <th>Дата/Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th colspan="2"></th>
        </tr>

    <c:forEach  items="${meals}" var="meal">
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
