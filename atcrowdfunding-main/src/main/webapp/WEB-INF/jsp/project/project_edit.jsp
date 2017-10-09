<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>众筹平台-项目分类</title>
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
				  <li class="active">修改</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="updateForm">
				  <div class="form-group">
					<label for="exampleInputPassword1">分类名称</label>
					<input type="text" class="form-control" id="fname" value="${type.name }">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">简介</label>
					<input type="text" class="form-control" id="fremark" value="${type.remark }">
				  </div>
				  <button id="edit_btn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
				  <button id="reset_btn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
			   	
			    /* 给编辑按钮绑定点击事件 */
			    $("#edit_btn").click(function() {
			    	var loadingIndex = -1 ;
			    	$.ajax({
			    		type:"POST",
			    		url:"${APP_PATH}/project/doEdit.do",
			    		data:{
			    			"name":$("#fname").val(),
			    			"remark":$("#fremark").val(),
			    			"id":${type.id}  // 通过EL表达式获取id
			    		},
			    		beforeSend:function(){
			    			loadingIndex = layer.msg('数据正在保存中', {icon: 16});
			    			return true ;
			    		},
			    		success : function(result){
			    			layer.close(loadingIndex);
			    			if(result.success){
			    				layer.msg("数据修改成功,正在跳转列表页面!", {time:1000, icon:6});
			    				
			    				window.location.href="${APP_PATH}/project/toProjectType.htm?pageno="+${param.pageno};
			    			}else{
			    				layer.msg("修改用户失败!", {time:2000, icon:5, shift:6});
			    			}
			    		},
			    		error:function(){
			    			layer.msg("修改用户失败!", {time:2000, icon:5, shift:6});
			    		}
			    	});
			    	return;
			    });
			    
			    
			    $("#reset_btn").click(function(){
			    	$("#updateForm")[0].reset(); //DOM对象中含有reset()函数,jquery中没有reset()函数
			    });
			    
			    
            });
        </script>
        <!-- 提取js脚本 --> 
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>