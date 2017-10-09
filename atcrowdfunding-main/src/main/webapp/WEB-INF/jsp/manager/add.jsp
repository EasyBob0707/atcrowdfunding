<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
  	<title>众筹平台-管理员后台管理</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<jsp:include page="/WEB-INF/jsp/common/managerinfo.jsp"></jsp:include>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<!-- 对通用的菜单栏进行提取  -->
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form>
				  <div class="form-group">
					<label for="exampleInputPassword1">登录账号</label>
					<input type="text" class="form-control" id="floginacct" placeholder="请输入登陆账号">
				  </div>
				  <div class="form-group">
					<label for="exampleInputUsername1">用户名称</label>
					<input type="text" class="form-control" id="fusername" placeholder="请输入用户名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">邮箱地址</label>
					<input type="email" class="form-control" id="femail" placeholder="请输入邮箱地址">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <button id="insertBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    
			    
			    $("#insertBtn").click(function(){
	    			
	    			/* 获取登录的用户账号  */
	    			var $loginacct = $("#floginacct");
	    			if($.trim($loginacct.val()) == "") {
	    				layer.msg("登录账号不允许为空!", {time:2000, icon:5, shift:6}, function(){
	    					//获取焦点
	    					$floginacct.focus();
	    	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    	    		}); //弹出时间，图标，特效
	    				return;
	    			}
	    			
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
			    	
			    	var loadingIndex = -1 ;
			    	$.ajax({
			    		type:"post",
			    		url:"${APP_PATH}/manager/doAdd.do",
			    		data:{
			    			"loginacct":$("#floginacct").val(),
			    			"username":$("#fusername").val(),
			    			"email":$("#femail").val()
			    		},
			    		beforeSend:function(){
			    			loadingIndex = layer.msg('数据正在保存中', {icon: 16});
			    			return true ;
			    		},
			    		success:function(result){
			    			layer.close(loadingIndex);
			    			if(result.success){
			    				layer.msg("数据保存成功,正在跳转列表页面!", {time:1000, icon:6});
			    				window.location.href="${APP_PATH}/manager/index.htm";
			    			}else{
			    				layer.msg(result.errorMessage, {time:2000, icon:5, shift:6});
			    			}
			    		},
			    		error:function(){
			    			layer.msg("添加用户失败!", {time:2000, icon:5, shift:6});
			    		}
			    		
			    	});
			    	
			    	return ;
			    	
			    });
			    
            });
            
          	//当元素失去焦点的时候触发(与注册时的loginacct字段不能重复业务类似, 直接调用LoginController中的方法)
    		$("#floginacct").blur(function(){
    			$.ajax({
    				type : "POST", //$.ajax()函数中{}里的属性名称可以不用引号引起来.
    				url : "${APP_PATH}/existLoginacct.do",
    				data : {
    					"loginacct" : $("#floginacct").val()
    				},
    				beforeSend : function() {
    					//一般是完成提交请求前的准备工作:例如表单数据校验.
    					return true; //继续发起ajax请求.
    				},
    				success : function(result) {
    					//判断后台数据的返回值
    					if (!result.success) {
    						layer.msg(result.errorMessage, {time:2000, icon:5, shift:6}, function(){
    							//获取焦点
    							$floginacct.focus();
    			    		}); 
    						return;
    					}
    				},
    				error : function() {
    					//表示服务器端处理请求失败,执行相关操作
    					layer.msg("注册失败!", {time:2000, icon:5, shift:6});
    				}
    			});
    			return false;
    		});
            
        </script>
        <!-- 提取js脚本 --> 
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
    