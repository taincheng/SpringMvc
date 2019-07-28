<%--
  Created by IntelliJ IDEA.
  User: Old_man
  Date: 2019/7/25
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
 <h1>Success JSP</h1>

test: ${requestScope.test }<br>
names: ${requestScope.names}<br>

request user: ${requestScope.user}<br>
request school: ${requestScope.school}<br>

session user: ${sessionScope.user}<<br>
session school: ${sessionScope.school}<br>



</body>
</html>
