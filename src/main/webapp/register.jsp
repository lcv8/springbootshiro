<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>user register</title>
</head>
<body>
    <h1>register</h1>
    <form action="${pageContext.request.contextPath}/user/register" method="post">
        username : <input type="text" name="username">  <br/>
        password : <input type="text" name="password"/> <br/>
        <input type="submit" value="go">
    </form>
</body>
</html>