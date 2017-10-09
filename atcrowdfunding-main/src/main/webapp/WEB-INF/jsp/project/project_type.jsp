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
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 项目分类</a></div>
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
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryText_btn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button id="deleteBatch" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/project/add.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th >序号</th>
				  <th width="30"><input id="checkboxAll" type="checkbox"></th>
                  <th width="300">分类名称</th>
                  <th>简介</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>

              </tbody>
              <tfoot>
              
              </tfoot>
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
			    
			    <c:if test="${not empty param.pageno}">
			    queryPageProject(${param.pageno});
			    </c:if>
			    <c:if test="${empty param.pageno}">
			    queryPageProject(1);
			    </c:if> 
			    
            });
            
            
            function pageChange(pageno){
            	//window.location.href="${APP_PATH }/projectType.do?pageno="+pageno;
            	queryPageManager(pageno);
            };
            
            
            /* 为queryText_btn按钮绑定模糊查询事件 */
            $("#queryText_btn").click(function(){
            	var queryText = $("#queryText").val();
            	if ($.trim(queryText)=="") {
            		layer.msg("查询条件值不允许为空,请输入查询条件!", {time:2000, icon:5, shift:6}, function(){
            			$("#queryText").focus();
            		}); 		
            		return ;
				}
            	/* 为dataObj添加属性，并赋值 */
            	dataObj.queryText = $.trim(queryText);
            	/* 调用分页查询数据的函数 */
            	queryPageProject(1);
            });
            
            
            /* 定义ajax的data变量 */
            var dataObj = { 
        			"pageno":1,
        			"pagesize":2
        		};
            
            /* 分页查询数据 */
            function queryPageProject(pageno){
            	dataObj.pageno = pageno;/* 将dataObj中的pageno属性赋值，使用当前函数传入的入参，覆盖掉之前的属性值 */
            	$.ajax({
            		type:"post",
            		url:"${APP_PATH}/project/reallyProjectType.do",
            		data:dataObj,
            		beforeSend:function(){
            			return true;
            		},
            		success:function(result){
            			if(result.success){
            				var page = result.page;
            				var data = page.data;
            				
            				var content='';
            				$.each(data,function(index,type){
            					content+='<tr>';
                 				content+='  <td>'+(index+1)+'</td>';
                 				content+='  <td><input class="checkboxClass" type="checkbox" value="'+type.id+'"></td>';
                 				content+='  <td>'+type.name+'</td>';
                 				content+='  <td>'+type.remark+'</td>';
                 				content+='  <td>';
                 				content+='	  <button onclick="window.location.href=\'${APP_PATH}/project/edit.htm?pageno='+page.pageno+'&id='+type.id+'\'" type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                 				content+='	  <button onclick="deleteType('+type.id+',\''+type.name+'\')" type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                 				content+='  </td>';
                 				content+='</tr>';	
            				 });
            				 
            				 $("tbody").html(content);//innerHTML
            				 
            				var contentNavigater = '';
            				 
             				contentNavigater+='<tr>';
            				contentNavigater+='	 <td colspan="6" align="center">';
            				contentNavigater+='		<ul class="pagination">';
            											if(page.pageno==1){
            				contentNavigater+='				<li class="disabled"><a href="#">上一页</a></li>';
            											}
            											if(page.pageno!=1){
            				contentNavigater+='				<li><a href="#" onclick="queryPageProject('+(page.pageno-1)+')">上一页</a></li>';
            											}
            											for(var i = 1;i <= page.totalno;i++){
            				contentNavigater+='				<li ';
            													if(page.pageno==i){
            				contentNavigater+='						class="active"';
            													}
            				contentNavigater+='				><a href="#" onclick="queryPageProject('+i+')">'+i+'</a></li>';
            												
            											}
														if(page.pageno==page.totalno){
            				contentNavigater+='				<li class="disabled"><a href="#">下一页</a></li>';
														}
														if(page.pageno!=page.totalno){
            				contentNavigater+='				<li><a href="#" onclick="queryPageProject('+(page.pageno+1)+')">下一页</a></li>';
														}

            				contentNavigater+='		 </ul>';
            				contentNavigater+='	 </td>';
            				contentNavigater+='</tr>';
            				
            				$("tfoot").html(contentNavigater);
            			}else{
            				alert("分页失败");
            				
            			};
            			
            		},
            		error:function(){
            			alert("分页查询失败！");
            		}
            	});
            };
            
            
            /* 删除一条type数据 */
            function deleteType(id,name){
            	var loadingIndex = -1 ;
            	layer.confirm("删除["+name+"]类型,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
	            	$.ajax({
	            		type:"post",
	            		url: "${APP_PATH}/project/dodelete.do",
	            		data:{
	            			"id":id
	            		},
	            		beforeSend:function(){
	            			loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
	            			return true;/* 放行 */
	            		},
	            		success:function(result){
	            			layer.close(loadingIndex);
	            			if(result.success){
	            				queryPageProject(1);
	            			}else{
	            				layer.msg("success删除失败!", {time:1000, icon:5, shift:6});
	            			}
	            		},
	            		error:function(){
	            			layer.msg("error删除失败!", {time:1000, icon:5, shift:6});
	            		}
	            	});
	            	layer.close(cindex);
            	}, function(cindex){
    			    layer.close(cindex);
            	});
            }
            
            
            /* 设置全选多选框函数 */
            $("#checkboxAll").click(function(){
            	/* 获取全选多选框当前被选中状态 */
            	var checkboxAllStatus = this.checked;
            	/* 
            		$(selector).each(function(index,element))
            		function(index,element)
					index - 选择器的 index 位置
					element - 当前的元素（也可使用 "this" 选择器）
            	*/
            	$(".checkboxClass").each(function(i,n){
            		n.checked = checkboxAllStatus;
            	});
            });
            	
           	/* 批量删除 */
           	$("#deleteBatch").click(function(){
           		/* 获取多选框的状态 */
           		var checkboxClass = $(".checkboxClass:checked");
           		/* 获取多选框被选中的数量 */
           		var length = checkboxClass.length;
           		
           		/* 拼接data */
           		var paramStr = "";
           		$.each(checkboxClass,function(i,n){
           			if(i!=0){
           				paramStr += "&";
           			}
           			paramStr += "id=" +n.value;
           		});
           		
           		/* 如果被选中的多选框的数量大于0 */
           		if(length>0){
           			layer.confirm("删除这些用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
           				$.ajax({
           					type:"post",
           					url:"${APP_PATH}/project/deleteBatch.do",
           					data:paramStr,
           					beforeSend:function(){
           						loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
           						return true;
           					},
           					success:function(result){
           						layer.close(loadingIndex);
           						if(result.success){
           							queryPageProject(1);
   	            				}else{
   	            					layer.msg(result.errorMessage, {time:1000, icon:5, shift:6});
   	            				}
           					},
           					error:function(){
           						layer.msg("删除失败啊", {time:1000, icon:6});
           					}
           				});
           				
           			}, function(cindex){
   	    			    layer.close(cindex);
   	    			});
           		}
           	});
            	
            	
            	
            
        </script>
        <!-- 提取js脚本 --> 
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
