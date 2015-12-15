<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>


  <title>哔哔电影</title>

  <link rel="icon" type="/image/ico" href="${ctx}/img/favicon.png"/>


</head>
<body>

<div class="errorPage">
  <p class="name">404</p>

  <p class="description">没有找到您请求的页面</p>

  <p>
    <button class="btn btn-danger" onClick="document.location.href = '/index';">回到主页</button>
    <button class="btn btn-warning" onClick="history.back();">返回上一页</button>
  </p>
</div>

</body>
</html>
</SCRIPT>
