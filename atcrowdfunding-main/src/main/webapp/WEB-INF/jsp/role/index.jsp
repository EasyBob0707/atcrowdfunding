<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
  	<title>众筹平台-角色维护</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
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
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button id="deleteBatch" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/role/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th>序号</th>
				  <th width="30"><input id="checkboxAll" type="checkbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
            <tbody>
                  <!-- <tr>
                  <td>1</td>
				  <td><input type="checkbox"></td>
                  <td>PM - 项目经理</td>
                  <td>
				      <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
				      <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
					  <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
				  </td> -->
                </tr>
              </tbody>
			  <tfoot>
			    <!--  <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
								<li class="disabled"><a href="#">上一页</a></li>
								<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">下一页</a></li>
							 </ul>
					 </td>
				 </tr> -->

			  </tfoot> 
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
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
			   	 queryPageRole(${param.pageno});
			    </c:if>
			    <c:if test="${empty param.pageno}">
			    queryPageRole(1);
			    </c:if>
            });
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignPermission.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            
            function pageChange(pageno){
            	//window.location.href="${APP_PATH}/role/toRole.do?pageon="+pageon;
            	queryPageRole(pageno);
            }
             var dataObj = {
            		"pageno" : 1,
            		"pagesize" :2
             };
             $("#queryBtn").click(function(){
            	 var queryText = $("#queryText").val();
            	 if($.trim(queryText)==""){ //判断文本框的值是否为空:应该使用双引号而不是null ;
             		layer.msg("查询条件值不允许为空,请输入查询条件!", {time:2000, icon:5, shift:6}, function(){
             			$("#queryText").focus();
             		}); 		
             		return ;
             	}
             	
             	dataObj.queryText = $.trim(queryText) ;
             	queryPageRole(1);
             });
            function queryPageRole(pageno){
            	dataObj.pageno = pageno ;
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/role/doIndex.do",
            		data :dataObj,
            		beforeSend : function(){
            			return true;
            		},
            		success : function(result){
                             if(result.success){
            				
            				var page = result.page ;
            				var data = page.data ; //roleList
            				
            				var content = '';
            				
            				//for(var i = 0 ; i< data.length;i++){}
            					$.each(data,function(index, role){
            					content+='<tr>';
                				content+='  <td>'+(index+1)+'</td>';
                				content+='  <td><input class="checkboxClass" type="checkbox" value="'+role.id+'"></td>';
                				content+='  <td>'+role.name+'</td>';
                				content+='  <td>';
                				content+='	  <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/role/assign.htm?roleId='+role.id+'\'"><i class=" glyphicon glyphicon-check"></i></button>';
                				content+='	  <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/role/edit.htm?pageno='+page.pageno+'&id='+role.id+'\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
                				content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteRole('+role.id+',\''+role.name+'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
                				content+='  </td>';
                				content+='</tr>';	
            				});
            				
            				
            				//$("tbody").append(content); //append()是在原有内容基础上追加内容,可能出现重复数据;
            				$("tbody").html(content); // innerHTML
            				
            				
            				var contentNavigater = '';
            				
            			
            				contentNavigater+='<tr>';
            				contentNavigater+='	 <td colspan="6" align="center">';
            				contentNavigater+='		<ul class="pagination">';
            											if(page.pageno==1){
            				contentNavigater+='				<li class="disabled"><a href="#">上一页</a></li>';
            											}
            											if(page.pageno!=1){
            				contentNavigater+='				<li><a href="#" onclick="queryPageRole('+(page.pageno-1)+')">上一页</a></li>';
            											}
            											for(var i = 1;i <= page.totalno;i++){
            				contentNavigater+='				<li ';
            													if(page.pageno==i){
            				contentNavigater+='						class="active"';
            													}
            				contentNavigater+='				><a href="#" onclick="queryPageRole('+i+')">'+i+'</a></li>';
            												
            											}
														if(page.pageno==page.totalno){
            				contentNavigater+='				<li class="disabled"><a href="#">下一页</a></li>';
														}
														if(page.pageno!=page.totalno){
            				contentNavigater+='				<li><a href="#" onclick="queryPageRole('+(page.pageno+1)+')">下一页</a></li>';
														}

            				contentNavigater+='		 </ul>';
            				contentNavigater+='	 </td>';
            				contentNavigater+='</tr>';

            				
            				$("tfoot").html(contentNavigater);
            			}else{
            				layer.msg(result.errorMessage, {time:1000, icon:5, shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("分页查询失败!",{time:1000,icon:5,shift:6});
            		}
            	});
            	return ;
            }
            
   			 function deleteRole(id,name){
            	
            	layer.confirm("删除["+name+"]角色,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
            		var loadingIndex = -1 ;
            		$.ajax({
            			type:"post",
            			url:"${APP_PATH}/role/doDelete.do",
            			data:{
            				"id":id
            			},
            			beforeSend:function(){
            				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
            				return true ;
            			},
            			success:function(result){
            				layer.close(loadingIndex);
            				if(result.success){
            					queryPageRole(1);
            				}else{
            					layer.msg("删除角色失败!", {time:1000, icon:5, shift:6});
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
   			 }
   			 
   			$("#checkboxAll").click(function() {
            	var checkboxAllStatus = this.checked ;
            	$(".checkboxClass").each(function(i,n){
            		n.checked = checkboxAllStatus;
            	});
            	
            });
        	
    	   $("#deleteBatch").click(function(){
    		//alert("进入批量删除ajax");
           	var checkBoxChecked = $(".checkboxClass:checked");
           	var length = checkBoxChecked.length;
           
           	var paramStr = "";
           	$.each(checkBoxChecked,function(i,n){
           		if(i!=0){
           			paramStr += "&";
           		}
           		paramStr += "id="+n.value ; // id=1&id=2&id=3
           	});
           	
        //$("input").remove(".queryText");
           	if(length>0){
           		layer.confirm("删除这些用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
               		var loadingIndex = -1 ;
            		$.ajax({
            			type:"post",
            			url:"${APP_PATH}/role/doDeleteBatch.do",
            			data:paramStr,
            			beforeSend:function(){
            				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
            				return true ;
            			},
            			success:function(result){
            				layer.close(loadingIndex);
            				if(result.success){
            					queryPageRole(1);
            					//window.location.href="${APP_PATH}/role/toRole.htm?pageno="+${param.pageno};
            				}else{
            					layer.msg("删除角色失败!", {time:1000, icon:5, shift:6});
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
