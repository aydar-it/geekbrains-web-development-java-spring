<%@ page contentType="text/html;charset=utf-8" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<html>
    <head>
        <title>Results</title>
        <meta charset="UTF-8">
    </head>
    <body>
    <table border="1">
        <tr><td><p>Фамилия</p></td><td><%= request.getParameter("surname") %></td></tr>
        <tr><td><p>Имя</p></td><td><%= request.getParameter("name") %></td></tr>
        <tr><td><p>Отчество</p></td><td><%= request.getParameter("patronymic") %></td></tr>
        <tr><td><p>Дата рождения</p></td><td><%= request.getParameter("birthday") %></td></tr>
        <tr><td><p>Город</p></td><td><%= request.getParameter("city") %></td></tr>
    </table>
    </body>
</html>















