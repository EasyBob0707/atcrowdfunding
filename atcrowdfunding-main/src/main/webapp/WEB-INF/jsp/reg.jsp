<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 使用spring的标签库方便数据的回显  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>众筹平台-注册</title>
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
					<a class="navbar-brand" href="${APP_PATH}/toDefault.htm" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">

		<form action="${APP_PATH}/doReg.do" class="form-signin" 
		role="form" method="post">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户注册
			</h2>
			<div class="form-group has-success has-feedback">
				<input type="text" name="username" class="form-control"
					id="fusername" placeholder="请输入真实姓名" autofocus /> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input onblur="checkLoginAcct()" type="text" name="loginacct" class="form-control"
					id="floginacct" placeholder="请输入登录账号" autofocus /> <span
					class="glyphicon glyphicon-qrcode form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="password" name="userpswd" class="form-control"
					id="fuserpswd" placeholder="请输入登录密码" style="margin-top: 10px;" />
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="text" name="email" class="form-control"
					id="femail" placeholder="请输入邮箱地址" style="margin-top: 10px;" />
				<span
					class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<select id="fusertype" name="usertype" class="form-control">
					<option value="member" selected>会员</option>
					<option value="manager">管理</option>
				</select>
			</div>
			<div class="checkbox">
				<label> 忘记密码 </label> <label style="float: right"> <a
					href="${APP_PATH}/toLogin.htm">我有账号</a>
				</label>
			</div>
			<a class="btn btn-lg btn-success btn-block" onclick="doReg()">注册</a>
		</form>
	</div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<!-- 提交表单数据(整个项目采用异步请求来完成请求) -->
	<script>

		function doReg() {
			/* 
			$("[class='btn btn-lg btn-success btn-block']").click(function() { */
			/* 
			同步开发使用的代码
			var href = this.href;
			$("form").attr("action", href).submit();
			return false;
			});
			*/
			
			/* 获取登录的用户名称  */
			var $fusername = $("#fusername");
			if ($.trim($fusername.val()) == "") {
				layer.msg("登录用户名称不允许为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$fusername.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
	    		return ;//单击事件函数返回
			}
			
			/* 获取登录的用户账号  */
			var $floginacct = $("#floginacct");
			if($.trim($floginacct.val()) == "") {
				layer.msg("登录账号不允许为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$floginacct.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
			
			/* 获取登录的用户密码  */
			var $fuserpswd = $("#fuserpswd");
			if($.trim($fuserpswd.val()) == "") {
				layer.msg("用户密码不允许为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$fuserpswd.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
			
			/* 获取登录的用户邮箱 */
			var $femail = $("#femail");
			if($.trim($femail.val()) == "") {
				layer.msg("用户邮箱不允许为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$femail.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
		
			 
			var reg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!reg.test($femail.val())){
				layer.msg("用户邮箱不符合规则!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$femail.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
			
			var regIndex = -1;
			$.ajax({
				type : "POST", //$.ajax()函数中{}里的属性名称可以不用引号引起来.
				url : "${APP_PATH}/doReg.do",
				data : {
					"username" : $fusername.val(),
					"loginacct" : $floginacct.val(),
					"userpswd" : $fuserpswd.val(),
					"email" : $femail.val(),
					"usertype" : $("#fusertype").val()
				},
				beforeSend : function() {
					regIndex = layer.msg("正在注册!", {time:1000, icon:6});
					//一般是完成提交请求前的准备工作:例如表单数据校验.
					return true; //继续发起ajax请求.
				},
				success : function(result) { //将服务器端返回的JSON格式字符串转换为JSON,然后通过JS进行解析.
					layer.close(regIndex);
					if(result.success) {
						// 表示服务器端成功处理请求,并返回结果的处理
						// alert($("#fusertype").val());
						if ($("#fusertype").val() == "member") {
							window.location.href = "${APP_PATH}/member.htm";
						} else if($("#fusertype").val() == "manager") {
							window.location.href = "${APP_PATH}/main.htm";
						}
					} else {
						layer.msg(result.errorMessage, {time:2000, icon:5, shift:6});
					}
				},
				error : function() {
					//表示服务器端处理请求失败,执行相关操作
					layer.msg("注册失败!", {time:2000, icon:5, shift:6});
				}
				
			});
		}

		//当元素失去焦点的时候触发(检查新注册的用户是否已经存在于数据库)
		function checkLoginAcct() {
			$.ajax({
				type : "POST",
				url : "${APP_PATH}/existLoginacct.do",
				data : {
					"loginacct" : $("floginacct").val() //如果在该方法当中输入参数, 说明是赋值操作
				},
              beforeSend : function() {
                //在此处可以做表单验证
                return true;
				},
                //测试浏览器返回的状态码(也会一种好的调试方法)
                /*statusCode: {
                    404: function() {
                        console.log("-1-1-1-1 WE GOT 404!");
                    },
                    200: function() {
                        console.log("-1-1-1-1 WE GOT 200!");
                    },
                    302: function() {
                        console.log("-1-1-1-1 WE GOT 302!");
                    }
                },*/
                success : function (result) {
					if(!result.success) {
						layer.msg(result.errorMessage, {time:2000, icon:5, shift:6}, function() {
							//获取焦点(文本域的焦点)
							$("#floginacct").focus();
						});
					}
				},
				error : function() {
					//表示服务器端处理请求失败,执行相关操作
					layer.msg("操作有误!", {time:2000, icon:5, shift:6});
				}
			});
		};
	</script>
</body>
</html>
