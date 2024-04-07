<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/04/2024
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
     <a href="http://localhost:8080/Gradle___com_example___demo8_1_0_SNAPSHOT_war/home?action=showAdd">Add</a>
     <input name="search">
     <a href="http://localhost:8080/Gradle___com_example___demo8_1_0_SNAPSHOT_war/home?action=search">Search</a>
     <table>
         <c:forEach items="${listEmployee}" var="item">
             <tr>
                 <td>${item.name}</td>
                 <td>${item.address}</td>
                 <td>${item.email}</td>
                 <td>${item.phonenumber}</td>
                 <td>${item.salary}</td>
                 <td>${item.department}</td>
                 <td>
                         <button><a href="http://localhost:8080/Gradle___com_example___demo8_1_0_SNAPSHOT_war/home?action=showEdit&EmployId=${item.id}">Edit</a></button>
                 </td>
                 <td>
                     <form action="http://localhost:8080/Gradle___com_example___demo8_1_0_SNAPSHOT_war/home?action=delete&EmployId=${item.id}" method="post">
                         <button>Delete</button>
                     </form>
                 </td>
             </tr>
         </c:forEach>
     </table>
</body>
</html>
