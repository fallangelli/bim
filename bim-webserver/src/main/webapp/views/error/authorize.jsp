<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  <link href="../../css/skin.css" rel="stylesheet" type="text/css"/>
  <title>错误</title>

  <script type="text/javascript">
    window.onload = function () {
      top.location.href = "/views/error/403.jsp"
    }
  </script>

</head>
</html>
