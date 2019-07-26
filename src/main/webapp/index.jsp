<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<a href="springmvc/testParamsAndHeader?username=haha&age=10">testParamsAndHeader</a><br>
<a href="helloworld">Hello world</a><br>
<a href="springmvc/testRequestMapping">testRequestMapping</a><<br>
<a href="springmvc/testMethod">testMethod</a>
<form action="springmvc/testMethod" method="post">
    <input type="submit" value="submit">
</form>
</body>
</html>
