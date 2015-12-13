<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>

<!DOCTYPE html>
<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
  <!--[if gt IE 8]>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <![endif]-->

  <title>哔哔电影</title>

  <link rel="icon" type="/image/ico" href="../../tis/favicon.ico"/>

  <link href="${ctx}/css/stylesheets.css" rel="stylesheet" type="text/css"/>
  <!--[if lt IE 8]>
  <link href="${ctx}/css/ie7.css" rel="stylesheet" type="text/css"/>
  <![endif]-->
  <link rel='stylesheet' type='text/css' href='${ctx}/css/fullcalendar.print.css' media='print'/>

  <%
    Throwable ex = null;
    if (exception != null)
      ex = exception;
    if (request.getAttribute("javax.servlet.error.exception") != null)
      ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
  %>


</head>
<body>

<div class="errorPage">

  <p class="name">500</p>

  <p class="description">服务器错误</p>

  <p>
    <button class="btn btn-success" onClick="document.location.href = '${ctx}/login';">重新登录</button>
    <button class="btn btn-danger" onClick="document.location.href = '/index';">回到主页</button>
    <button class="btn btn-warning" onClick="history.back();">返回上一页</button>
  </p>

  <p><%=ex.getMessage()%>！</p>
</div>

</body>
</html>

</SCRIPT>
