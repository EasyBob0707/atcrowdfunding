<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
   <title>众筹平台-管理员后台管理</title>
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
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<%-- 引入commom中的userinfo.jsp --%>
				<%-- (动态导入)编译完成在进行导入 --%>
				<jsp:include page="/WEB-INF/jsp/common/managerinfo.jsp"/>
				<%-- (静态导入)将整个页面包含进来, 然后随目标页面一起进行编译  --%>
				<%-- <%@ include file="/common/userinfo.jsp"%> --%>
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
  <button id="queryBtn" type="button"  class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" id="deleteBatch"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/manager/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th>序号</th>
				  <th width="30"><input id="checkboxAll" type="checkbox"></th>
                  <th>账号</th>
                  <th>名称</th>
                  <th>邮箱地址</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              	<!-- AJAX动态添加数据信息  -->
              </tbody>
			  <tfoot>
			  	<!-- AJAX动态添加数据信息  -->
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
			    
			    /* 用jstl做判断是为了获取数据简单, 但是这里也支持jstl标签库 */
			    /* 用if语句也可以实现, 先要获取到值然后进行判断  */
			    /* 用于修改信息跳回到修改前的页码页码  */
			    <c:if test="${not empty param.pageno}">
			    	queryPageManager(${param.pageno});
			    </c:if>
			    <c:if test="${empty param.pageno}">
			    	queryPageManager(1);
			    </c:if>
			    
			    /* 
			    if(${not empty param.pageno}){
			    	queryPageUser(${param.pageno});
			    }else if (${empty param.pageno}){
			    	 queryPageUser(1);
			    }
			    */
			    
			    //queryPageManager(1);
            });
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            
            <%--
            function pageChange(pageno){
	        	/* 属于前台路径  */
	        	window.location.href="${APP_PATH}/manager/index.do?pageno="+pageno;
	        }
	        --%>
	        
	        var dataObj = {
       			"pageno" : 1,
       			"pagesize" : 2 
	        };
	        
	        //数据检索查询
	        $("#queryBtn").click(function(){
	        	/* 获取查询框中的条件   */
	        	var $queryText = $("#queryText").val();
	        	//判断文本框的值是否为空:应该使用双引号而不是null ;
	        	if($.trim($queryText)=="") {
            		layer.msg("查询条件值不允许为空,请输入查询条件!", {time:2000, icon:5, shift:6}, function(){
            			$("#queryText").focus();
            		}); 		
            		return ;
            	}
            	
            	dataObj.queryText = $.trim($queryText) ;
            	
            	queryPageManager(1, false);
	        });
	         
            <%-- Ajax的异步分页代码   --%>
	        function queryPageManager(pageno, flag) {
	        	//用于批量删除(避免先查询在进行删除, 返回空页面)
	        	if(flag==true){
	        		dataObj.queryText="";
	        		$("#queryText").val("");
	        		$("#checkboxAll").attr("checked", false);
	        	}
	        	/* 如果不是检索查询, 分页查询还是要传入pageno参数  */
	        	dataObj.pageno = pageno;
	        	$.ajax({
	        		type : "POST",
	        		url : "${APP_PATH}/manager/doIndex.do",
	        		data : dataObj,
	        		beforeSend : function(){
	        			return true ;
	        		},
	        		success : function(result) {
	        			if(result.success) {
	        				var page = result.page ;
	        				var data = page.data ; //userList
	        				var content = '';
	        				
	        				/* 
		        				data指的是返回的List<Manager>数据, manager指的是
		        				List<Manager>中的每一个元素的数据, index指的是每一个
		        				元素数据的下标, index+1表示的是序号
	        				*/
	        				$.each(data, function(index, manager){
	        					content+='<tr>';
	            				content+='  <td>'+(index+1)+'</td>';
	            				content+='  <td><input class="checkboxClass" value="'+manager.id+'" type="checkbox"></td>';
	            				content+='  <td>'+manager.loginacct+'</td>';
	            				content+='  <td>'+manager.username+'</td>';
	            				content+='  <td>'+manager.email+'</td>';
	            				content+='  <td>';
	            				content+='	  <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/manager/toAssignRole.htm?id='+manager.id+'\'"><i class=" glyphicon glyphicon-check"></i></button>';
	            				content+='	  <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/manager/edit.htm?pageno='+page.pageno+'&id='+manager.id+'\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
	            				content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteManager('+manager.id+',\''+manager.loginacct+'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
	            				content+='  </td>';
	            				content+='</tr>';	
	        				});
	        				
	        				
	        				//$("tbody").append(content); //append()是在原有内容基础上追加内容,可能出现重复数据;
	        				$("tbody").html(content); // innerHTML
	        				
	        				 
	        				var contentNavigater = '';
	        				
	        				contentNavigater+='<tr>';
	        				contentNavigater+='	 <td colspan="6" align="center">';
	        				contentNavigater+='		<ul class="pagination">';
	        				
	        				/* 判断当前位于第一页的时候, 上一页的按钮禁用 */
	        				 if(page.pageno == 1){
        						contentNavigater+='	<li class="disabled"><a href="#">上一页</a></li>';	
	        				} 
	        				
	        				if(page.pageno != 1) {
	        					contentNavigater+='	<li><a href="#" onclick="queryPageManager('+(page.pageno-1)+','+false+')">上一页</a></li>';
	        				}
	        				
	        				for ( var i = 1 ; i <= page.totalno ; i++) {
	        					
	        					if(i == page.pageno){	
	        						
	        						contentNavigater += '<li class="active"><a href="#" onclick="queryPageManager('+i+','+false+')">'+i+'</a></li>';            						
	        					
	        					} else { 
	        						
	        						contentNavigater += '<li><a href="#" onclick="queryPageManager('+i+','+false+')">'+i+'</a></li>';  
	        					
	        					}   						
	        				}
							
	        				if(page.pageno == page.totalno){
	        					contentNavigater+='	<li class="disabled"><a href="#">下一页</a></li>';
	        				}
	        				
	        				if(page.pageno != page.totalno){
	        					contentNavigater+='	<li><a href="#" onclick="queryPageManager('+(page.pageno+1)+','+false+')">下一页</a></li>';
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
	        			layer.msg("分页查询失败!", {time:1000, icon:5, shift:6});
	        		}
	        		
	        	});
	        	
	        	return ;
	        }
	        
	        /* 单条删除操作  */
			function deleteManager(id,loginacct) {
            	
            	layer.confirm("删除["+loginacct+"]用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
            		var loadingIndex = -1 ;
            		$.ajax({
            			type : "POST",
            			url : "${APP_PATH}/manager/doDelete.do",
            			data : {
            				"id" : id
            			},
            			beforeSend:function(){
            				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
            				return true ;
            			},
            			success : function(result){
            				layer.close(loadingIndex);
            				if(result.success) {
            					queryPageManager(1, false);
            				} else {
            					layer.msg("删除失败!", {time:1000, icon:5, shift:6});
            				};
            			},
            			
            			error: function(XMLHttpRequest, textStatus, errorThrown) {
                            alert(XMLHttpRequest.status);
                            alert(XMLHttpRequest.readyState);
                            alert(textStatus);
                        }

            		});
            		
            		
    			    layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            	
            }
            
	        /* 批量进行删除(checkbox全选)  */
            $("#checkboxAll").click(function() {
            	
            	var checkboxAllStatus = this.checked ;
            	
            	$(".checkboxClass").each(function(i,n){
            		n.checked = checkboxAllStatus;
            	});
            	
            	/* $.each(array,function(i,n){}); */
            	
            });
            
	        /* 批量删除按钮  */
            $("#deleteBatch").click(function(){
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
	            			type : "POST",
	            			url : "${APP_PATH}/manager/doDeleteBatch.do",
	            			data : paramStr,
	            			beforeSend : function(){
	            				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
	            				return true ;
	            			},
	            			success : function(result){
	            				layer.close(loadingIndex);
	            				if(result.success){
	            					queryPageManager(1, true);
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
        <!-- 提取js脚本用于菜单栏选中项标红  --> 
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
