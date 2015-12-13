<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
  <!--[if gt IE 8]>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <![endif]-->

  <title>哔哔电影</title>

  <link rel="icon" type="/image/ico" href="${ctx}/img/favicon.png"/>

  <link href="${ctx}/css/stylesheets.css" rel="stylesheet" type="text/css"/>
  <!--[if lt IE 8]>
  <link href="${ctx}/css/ie7.css" rel="stylesheet" type="text/css"/>
  <![endif]-->
  <link rel='stylesheet' type='text/css' href='${ctx}/css/fullcalendar.print.css' media='print'/>

  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  <title>系统异常</title>
  <%
    Throwable ex = null;
    if (exception != null)
      ex = exception;
    if (request.getAttribute("javax.servlet.error.exception") != null)
      ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

    //记录日志
    Logger logger = LoggerFactory.getLogger("violation.jsp");
    logger.error(ex.getMessage(), ex);
  %>

</head>

<body>

<div class="errorPage">
  <p class="description"> 数据冲突！</p>

  <p class="description"><%=ex.getMessage()%>
  </p>

  <p>
    <button class="btn btn-warning" onClick="history.back();">返回上一页</button>
  </p>
</div>


</body>
</html>
