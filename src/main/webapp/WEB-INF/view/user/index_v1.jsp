<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<!DOCTYPE html>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>

<head>
    <meta charset="utf-8">
    <title>主页</title>
    <link href="${pageContext.request.contextPath}/assets/css/style.css?v=4.1.2" rel="stylesheet">
</head>
<body style="height:auto;background-color:#f0f3f4;">
    <div class="wrapper wrapper-content">
       <h1 class="logo-name">WELCOME</h1>
    </div>
</body>
</html>