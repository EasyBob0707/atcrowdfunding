<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>尚筹网-资质维护</title>
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
				<jsp:include page="/WEB-INF/jsp/common/managerinfo.jsp"/>
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
				<form role="form">
				  <div class="form-group">
					<label for="exampleInputPassword1">名称</label>
					<input type="hidden" id="fid" name="id" value="${param.id }">
					<input type="text" class="form-control" id="fname" name="name" value="${param.name }" placeholder="请输入证件名称">
				  </div>
				<!--   <div class="form-group">
					<label for="exampleInputPassword1">用户名称</label>
					<input type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">邮箱地址</label>
					<input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div> -->
				  <c:if test="${empty param.id}">
				  	<button type="button" id="add_btn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  </c:if>
				  <c:if test="${!empty param.id}">
				  	<button type="button" id="update_btn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改</button>
				  </c:if>
				  <button type="button" id="resetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
			    
			    $("#add_btn").click(function(){
			    	var certname = $("#fname").val();
			    	$.ajax({
			    		type:"POST",
			    		url:"${APP_PATH}/cert/addCert.do",
			    		data:{
			    			"name":certname
			    		},
			    		beforeSend:function(){
			    			return true;
			    		},
			    		success:function(result){
			    			if(result.success){
			    				window.location.href="${APP_PATH}/cert/index.do";
			    			}else{
			    				alert("添加失败！");
			    			}
			    		},
			    		error:function(){
			    			
			    		}
			    	});
			    });
			    
			    $("#update_btn").click(function(){
			    	var certid = $("#fid").val();
			    	var certname = $("#fname").val();
			    	if($.trim(certname)==""){
			    		alert("证件名称不能为空！");
			    		return;
			    	}
			    	$.ajax({
			    		type:"POST",
			    		url:"${APP_PATH}/cert/updateCert.do",
			    		data:{
			    			"id":certid,
			    			"name":certname
			    		},
			    		beforeSend:function(){
			    			return true;
			    		},
			    		success:function(result){
			    			if(result.success){
			    				window.location.href="${APP_PATH}/cert/index.do";
			    			}else{
			    				alert("修改失败！");
			    			}
			    		},
			    		error:function(){
			    			
			    		}
			    	});
			    });
			    
			    $("#resetBtn").click(function(){
			    	$("form")[1].reset();
			    });
			    
            });
        </script>
        <!-- 提取js脚本 --> 
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
