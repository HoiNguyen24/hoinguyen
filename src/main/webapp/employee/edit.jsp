<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/04/2024
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/Gradle___com_example___demo8_1_0_SNAPSHOT_war/home?action=update" method="post">
    <div>
        <label for="name">Name</label>
        <input type="text" name="name" placeholder="name" id="name" value="${em.name}">
    </div>
    <div>
        <label for="address">Address</label>
        <input type="text" name="address" placeholder="address" id="address" value="${em.address}">
    </div>
    <div>
        <label for="email">Email</label>
        <input type="text" name="email" placeholder="email" id="email" value="${em.email}">
    </div>
    <div>
        <label for="phonenumber">Phonenumber</label>
        <input type="text" name="phonenumber" placeholder="phonenumber" id="phonenumber" value="${em.phonenumber}">
    </div>
    <div>
        <label for="salary"></label>
        <input type="number" name="salary" placeholder="salary" id="salary" value="${em.salary}">
    </div>
    <div>
        <select name="department" id="department">
            <c:forEach var="item" items="${departments}">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <button type="submit">Thêm</button>
    </div>
</form>
</body>
</html>
