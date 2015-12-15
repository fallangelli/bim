<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>


  <title>哔哔电影</title>


</head>
<body>

<div class="errorPage">
  <p class="name">504</p>

  <p class="description">连接超时</p>

  <p>
    <button class="btn btn-success" onClick="document.location.href = '${ctx}/login';">重新登录</button>
    <button class="btn btn-danger" onClick="document.location.href = '/index';">回到主页</button>
    <button class="btn btn-warning" onClick="history.back();">返回上一页</button>
  </p>
</div>

</body>
</html>
</SCRIPT>
