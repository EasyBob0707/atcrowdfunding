<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table 
 td:nth-child(even) {
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
						- 资质管理</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<jsp:include page="/WEB-INF/jsp/common/managerinfo.jsp"/>
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
					<!-- 对通用的菜单栏进行提取  -->
					<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input class="form-control has-success" id="query_text" name="queryText" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" id="search_btn" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" class="btn btn-danger" id="delete_btn"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='${APP_PATH}/cert/edit.htm'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="45">序号</th>
										<th width="30"><input id="checkboxAll" type="checkbox"></th>
										<th>名称</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- <tr>
										<td>1</td>
										<td><input type="checkbox"></td>
										<td>营业执照副本</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr>
									<tr>
										<td>3</td>
										<td><input type="checkbox"></td>
										<td>组织机构代码证</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr>
									<tr>
										<td>4</td>
										<td><input type="checkbox"></td>
										<td>单位登记证件</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr>
									<tr>
										<td>5</td>
										<td><input type="checkbox"></td>
										<td>法定代表人证件</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr>
									<tr>
										<td>6</td>
										<td><input type="checkbox"></td>
										<td>经营者证件</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr>
									<tr>
										<td>7</td>
										<td><input type="checkbox"></td>
										<td>手执身份证照片</td>
										<td>
											<button type="button" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr> -->
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
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
			query();
		});
		
		$("#search_btn").click(function(){
			var queryText = $("#query_text").val();
			if($.trim(queryText) == ""){
				alert("查询条件不能为空！");
				return;
			}
			query();
		});
		
		function query(){
			var queryText = $.trim($("#query_text").val());
			$.ajax({
				type : "POST",
				url : "${APP_PATH}/cert/listCert.do",
				data : {
					"queryText":queryText
				},
				beforeSend : function() {
					return true;
				},
				success : function(result) {
					if (result.success) {
						var certs = result.list;
						var content = "";
						$.each(certs,function(i,cert){
							content+='<tr>';
								content+='	<td>'+(i+1)+'</td>';
								content+='	<td><input type="checkbox" class="checkboxClass" value="' + cert.id +'"></td>';
								content+='	<td>'+ cert.name+'</td>';
								content+='	<td>';
								content+='		<button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/cert/edit.htm?id='+cert.id+'&name='+cert.name+'\'">';
								content+='			<i class=" glyphicon glyphicon-pencil"></i>';
								content+='		</button>';
								content+='		<button type="button" class="btn btn-danger btn-xs" onclick=deleteCert('+cert.id+')> ';
								content+='			<i class=" glyphicon glyphicon-remove"></i>';
								content+='		</button> ';
								content+='	</td>';
								content+='</tr>';
						})
						
						$("tbody").html(content);
					} else {
						layer.msg(result.errorMessage, {
							time : 1000,
							icon : 5,
							shift : 6
						});
					}
				},
				error : function() {

				}
			});
		}
		
		function deleteCert(id){
				if(confirm("确认删除吗？")){
					$.ajax({
						type:"POST",
						url:"${APP_PATH}/cert/deleteCert.do",
						data:{
							"id":id	
						},
						beforeSend:function(){
							return true;
						},
						success:function(result){
							if(result.success){
								window.location.href="${APP_PATH}/cert/index.do";
							}else{
								alert(result.errorMessage);
							}
						},
						error:function(){
						}
					});
				}
		}
		
		 $("#checkboxAll").click(function(){
         	var checkboxAllStatus = this.checked ;
         	$(".checkboxClass").each(function(i,n){
         		n.checked = checkboxAllStatus;
         	});
         });
		
		$("#delete_btn").click(function(){
			var checkBoxChecked = $(".checkboxClass:checked");
        	var length = checkBoxChecked.length;
        	var paramStr = "";
        	$.each(checkBoxChecked,function(i,n){
        		if(i!=0){
        			paramStr += "&";
        		}
        		paramStr += "id="+n.value ; // id=1&id=2&id=3
        	});
        	
        	
        	if(length>0){
        		layer.confirm("删除这些用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
            		var loadingIndex = -1 ;
            		$.ajax({
            			type:"post",
            			url:"${APP_PATH}/cert/doDeleteBatch.do",
            			data:paramStr,
            			beforeSend:function(){
            				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
            				return true ;
            			},
            			success:function(result){
            				layer.close(loadingIndex);
            				if(result.success){
            					query();
            				}else{
            					layer.msg("删除失败!", {time:1000, icon:5, shift:6});
            				}
            			},
            			error:function(){
            				layer.msg("删除失败!", {time:1000, icon:5, shift:6});
            			}
            			
            		});

    			    layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
        	}else{
        		layer.msg("请选择要删除的用户!", {time:1000, icon:6});
        	}
		});
	</script>
	<!-- 提取js脚本 --> 
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
</body>
</html>
