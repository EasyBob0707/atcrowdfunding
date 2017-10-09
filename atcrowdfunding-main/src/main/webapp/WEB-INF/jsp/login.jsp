<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用于回写角色信息数据  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>众筹平台-登录</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/login.css">
<style>
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="index.html" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<form action="" class="form-signin"
			role="form" method="post">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户登录
			</h2>
			<div class="form-group has-success has-feedback">
				<input type="text" name="loginacct" class="form-control"
					id="floginacct" value="${param.loginacct }" placeholder="请输入登录账号" autofocus> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="password" name="userpswd" class="form-control"
					id="fuserpswd" value="${param.userpswd }" placeholder="请输入登录密码" style="margin-top: 10px;">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<select id="fusertype" name="usertype" class="form-control">
					<option value="member" selected>会员</option>
					<option value="manager">管理</option>

					<!-- 动态加载角色信息  -->
					<!-- 
               	<c:forEach items="${roles}" var="role">
					<option value="${role.id}">${role.name}</option>
				</c:forEach>
				 -->
				</select>
			</div>
			<div class="checkbox">
				<label> <input id="rememberme" type="checkbox" value="1">
					记住我
				</label> <br> <label> 忘记密码 </label> <label style="float: right">
					<a href="${APP_PATH}/toReg.htm">我要注册</a>
				</label>
			</div>
			<a class="btn btn-lg btn-success btn-block" onclick="dologin()">登录</a>
			<!-- 
			<input type="submit" class="btn btn-lg btn-success btn-block"
				value="登录" /> 
			-->
		</form>
	</div>
	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script>
		function dologin() {
		/* 静态页面为了实现动态效果  */
		<%--
        var type = $(":selected").val();
        if ( type == "user" ) {
            /* window.location.href = "main.html"; */
        	window.location.href = "${APP_PATH}/user/login.do";
        } else {
            /* window.location.href = "index.html"; */
        	window.location.href = "${APP_PATH}/user/login.do";
        } 
        --%>
			var floginacct = $("#floginacct");
			//去掉前后两端空格
			if ($.trim(floginacct.val()) == "") {
				layer.msg("登录用户名称不允许为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					floginacct.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
	    		return ;//单击事件函数返回
			}

			var fuserpswd = $("#fuserpswd");
			if ($.trim(fuserpswd.val()) == "") {
				layer.msg("登录用户密码不允许为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					fuserpswd.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
	    		return ;//单击事件函数返回
			}
			
			//Jquery对象转换为Dom对象, 再调用方法
			var flag = $("#rememberme")[0].checked; //是否选中【记住我】
			
			var loadingIndex = -1;
			$.ajax({
				type : "POST", //$.ajax()函数中{}里的属性名称可以不用引号引起来.
				url : "${APP_PATH}/doLogin.do",
				data : {
					"loginacct" : floginacct.val(),
					"userpswd" : fuserpswd.val(),
					"usertype" : $("#fusertype").val(),
					"flag" : flag ? "1" : "0"
				},
				beforeSend : function() {
					loadingIndex = layer.load(2, {time: 10*1000});
					// 一般是完成提交请求前的准备工作:例如表单数据校验.
					return true; //继续发起ajax请求.
				},
				success : function(result) { //将服务器端返回的JSON格式字符串转换为JSON,然后通过JS进行解析.
					layer.close(loadingIndex);
					//表示服务器端成功处理请求,并返回结果的处理
					//alert($("#fusertype").val());
					if (result.success) {
						//如果是管理员登录
						if($("#fusertype").val() == "manager"){
							window.location.href = "${APP_PATH}/main.htm";
						//如果是会员登录
						} else if ($("#fusertype").val() == "member"){
							window.location.href = "${APP_PATH}/member.htm";
						}
						
					} else {
						layer.msg(result.errorMessage, {time:2000, icon:5, shift:6}, function(){
			    			floginacct.focus();
			    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
			    		}); //弹出时间，图标，特效
			    		return ;//单击事件函数返回
					}
				},
				error : function() {
					//表示服务器端处理请求失败,执行相关操作
					layer.msg("登录失败!", {time:2000, icon:5, shift:6});
				}
			});
		}
	</script>
</body>
</html>