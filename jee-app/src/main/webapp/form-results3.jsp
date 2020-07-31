<%@ page contentType="text/html;charset=utf-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    int num = Integer.parseInt(request.getParameter("num"));
%>
<html>
    <head>
        <title>Results</title>
        <meta charset="UTF-8">
    </head>
    <body>
    <table border="1">
        <%
            for (int i = 1; i <= num; i++) {
                out.println("<tr>");
                for (int j = 1; j <= num; j++) {
                    out.println("<td>" + i + " " + j + "</td>");
                }
                out.println("</tr>");
            }
        %>
    </table>
    </body>
</html>















