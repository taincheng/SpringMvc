<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>

<a href="springmvc/testServletAPI">test ServletAPI</a>
<br>


<form action="springmvc/testPojo" method="post">
    username: <input type="text" name="username"/>
    <br>
    password: <input type="password" name="password"/>
    <br>
    email: <input type="text" name="email"/>
    <br>
    age: <input type="text" name="age"/>
    <br>
    city: <input type="text" name="address.city"/>
    <br>
    province: <input type="text" name="address.province"/>
    <br>
    <input type="submit" value="submit"/>
</form>

<br>


<a href="springmvc/testCookiesValue">testCookiesValue</a>

<br>


<a href="springmvc/testRequestHeader">testRequestHeader</a>

<br>

<a href="springmvc/testRequestParam?username=zhang&password=123">testRequestParam</a>

<br>

<form action="springmvc/testRest/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="testRest PUT"/>
</form>

<br>

<form action="springmvc/testRest/1" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="testRest DELETE"/>
</form>

<br>

<form action="springmvc/testRest" method="post">
    <input type="submit" value="testRest post"/>
</form>

<br>

<a href="springmvc/testRest/1">test Rest Get</a>

<br>

<a href="springmvc/testPathVaribale/123">testPathVaribale</a><br>
<a href="springmvc/testAnt/xxxxx/ant">testAnt</a><br>
<a href="springmvc/testParamsAndHeader?username=haha&age=10">testParamsAndHeader</a><br>
<a href="helloworld">Hello world</a><br>
<a href="springmvc/testRequestMapping">testRequestMapping</a><br>
<a href="springmvc/testMethod">testMethod</a>
<form action="springmvc/testMethod" method="post">
    <input type="submit" value="submit">
</form>
</body>
</html>
