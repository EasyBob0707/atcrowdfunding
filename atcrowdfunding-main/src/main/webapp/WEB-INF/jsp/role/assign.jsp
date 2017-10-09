<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/main.css">
<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">

<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}
</style>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 许可维护</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;"><%@include
							file="/WEB-INF/jsp/common/managerinfo.jsp"%>
					</li>
					<li style="margin-left: 10px; padding-top: 8px;">
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
					<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 权限菜单列表
						</h3>
					</div>
					<div class="panel-body">
						<button id="rolePermissionAssign" class="btn btn-success">分配许可</button>
						<br> <br>
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});

		});
	</script>
	<script type="text/javascript">
		var setting = {
			check : {
				enable : true
			},
			view : {
				selectedMulti : false, // 多选
				addDiyDom : function(treeId, treeNode) {
					var icoObj = $("#" + treeNode.tId + "_ico"); // tId = treeDemo_1, $("#treeDemo_1_ico")
					if (treeNode.icon) {
						icoObj.removeClass("button ico_docu ico_open")
								.addClass(treeNode.icon).css("background", "");
					}
				},
			},
			async : {
				enable : true,
				//url:"${APP_PATH}/permission/asyncLoadData.do", //异步加载许可树-维护许可树(增,删,改)
				url : "${APP_PATH}/permission/asyncLoadDataAssign.do?roleId=${role.id}", //异步加载许可树-分配许可
				autoParam : [ "id", "name=n", "level=lv" ]
			},

		};

		$.fn.zTree.init($("#treeDemo"), setting);
		
		/* 分配许可按钮事件  */
		$("#rolePermissionAssign").click(function() {
			//读取当前树对象
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");

			var nodes = treeObj.getCheckedNodes(true); // 获取被选中的节点
			
			var dataObj = {
				"roleId" : "${role.id}"
			}; 
			
			$.each(nodes, function(i, n) {
				dataObj["ids[" + i + "]"] = n.id; //  n.id的id表示Permission对象的id属性;
			});
			
			$.ajax({
				type : "POST",
				url : "${APP_PATH}/role/doAssignRolePermission.do",
				data : dataObj,
				success : function(result) {
					if (result.success) {
						layer.msg("给[${role.name}]分配许可成功!", {
							time : 1000,
							icon : 6
						});
					} else {
						layer.msg("给[${role.name}]分配许可失败!", {
							time : 1000,
							icon : 5,
							shift : 6
						});
					}
				}

			});

		});
	</script>
	<script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
</body>

</html>