<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>title</title>
</head>
<body>
    <h1>系统主页 v1.0 </h1>
    <a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
    <shiro:hasRole name="user">
        <a href="">用户管理</a>
    </shiro:hasRole>
    <shiro:hasRole name="user">
        <shiro:hasPermission name="user:add:*">
            <a href="">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:undate:*">
            <a href="">更新</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:delete:*">
            <a href="">删除</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:select:*">
            <a href="">查询</a>
        </shiro:hasPermission>
    </shiro:hasRole>
    <shiro:hasRole name="admin">
        <a href="">物流管理</a>
        <a href="">仓库管理</a>
        <a href="">人员管理</a>
    </shiro:hasRole>
    <shiro:hasAnyRoles name="admin,user,product">
        <a href="">物流信息</a>
    </shiro:hasAnyRoles>
</body>
</html>