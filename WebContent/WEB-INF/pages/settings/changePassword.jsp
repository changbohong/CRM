<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>修改密码</title>
<meta charset="utf-8">

<!-- Bootstrap样式 -->
<link rel="stylesheet" href="/CRM/css/bootstrap.min.css">
<script src="/CRM/js/jquery.min.js" type="text/javascript"></script>
<script src="/CRM/js/bootstrap.min.js" type="text/javascript"></script>

<link rel="stylesheet" href="/CRM/css/logo.left.css">

<!-- 对密码进行md5加密，依赖jQuery -->
<script src="/CRM/js/jQuery.md5.js" type="text/javascript"></script>
<script src="/CRM/js/encryptPassword.js" type="text/javascript"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<script src="/CRM/js/html5shiv.min.js" type="text/javascript"></script>
<script src="/CRM/js/respond.min.js" type="text/javascript"></script>

<s:head />
</head>
<body>
	<img class="img-responsive" src="/CRM/images/logo.png" alt="logo"
		width="394px" height="75px">
	<hr>
	<div class="panel panel-info">
		<div class="panel-heading">修改密码</div>
		<div class="panel-body">
			<form action="/CRM/settings/changePasswordAction" method="post">
				<s:fielderror />
				<div class="form-group">
					<label for="oldPassword">原密码：</label> <input class="form-control"
						id="oldPassword" name="oldPassword" type="password">
				</div>
				<div class="form-group">
					<label for="newPassword">新密码：</label> <input class="form-control"
						id="newPassword" name="newPassword" type="password">
				</div>
				<div class="form-group">
					<label for="confirmPassword">确认密码：</label> <input
						class="form-control" id="confirmPassword" name="confirmPassword"
						type="password">
				</div>
				<input class="btn btn-success btn-block" type="submit" value="确定">
			</form>
		</div>
	</div>
</body>
</html>
