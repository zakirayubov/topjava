<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Meal</title>
</head>
<style>
    dl {
        background: none repeat scroll 0 0 #FAFAFA;
        margin: 8px 0;
        padding: 0;
    }
    dt {
        display: inline-block;
        width: 170px;
    }
    dd {
        display: inline-block;
        margin-left: 8px;
        vertical-align: top;
    }
</style>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>

<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>DateTime:</dt>
        <dd> <input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd> <input type="text" value="${meal.description}" size=40 name="description"  required></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd> <input type="number" value="${meal.calories}" name="calories" required></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
