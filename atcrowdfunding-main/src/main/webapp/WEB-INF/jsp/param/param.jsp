<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
  	<title>众筹平台-消息模板</title>
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
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 参数管理</a></div>
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
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline"  style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
    <button id="queryBtn" type="button"  class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="45">序号</th>
                  <th>名称</th>
                  <th>代码</th>
                  <th>值</th>
                  <th width="100">修改</th>
                </tr>
              </thead>
              <tbody>
                
              </tbody>
               <tfoot>
			  	
			     <tr>
				     <td colspan="6" align="center">
						<ul class="pagination">							
							
						 </ul>
					 </td>
				 </tr>
 				
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
			    
			    <c:if test="${empty param.pageno}">
				queryPageParam(1);
			</c:if>
			<c:if test="${!empty param.pageno}">
				queryPageParam(${param.pageno});
			</c:if>
         });
         
         $("tbody .btn-success").click(function(){
             window.location.href = "assignRole.html";
         });
         
         $("tbody .btn-primary").click(function(){
             window.location.href = "edit.html";
         });
         
        
	        
	        var dataObj = {
    			"pageno" : 1,
    			"pagesize" : 2 
	        };
	        
	        
	        //数据检索查询
	        $("#queryBtn").click(function(){
	        	/* 获取查询框中的条件   */
	        	var queryText = $("#queryText").val();
	        	//判断文本框的值是否为空:应该使用双引号而不是null ;
	        	if($.trim(queryText)=="") {
         		layer.msg("查询条件值不允许为空,请输入查询条件!", {time:2000, icon:5, shift:6}, function(){
         			$("#queryText").focus();
         		}); 		
         		return ;
         		}
	        	dataObj.queryText = $.trim(queryText) ;
	        	queryPageParam(1);
	        });  
	        
	        
	       
	        
	         
         <%-- Ajax的异步分页代码   --%>
	        function queryPageParam(pageno) {
	        	/* 如果不是检索查询, 分页查询还是要传入pageno参数  */
	        	dataObj.pageno = pageno;
	        	$.ajax({
	        		type : "POST",
	        		url : "${APP_PATH}/param/doParam.do",
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
	        				$.each(data, function(index, param){
	        				content+='<tr>';
             				content+='  <td>'+(index+1)+'</td>';
             	
             				content+='  <td>'+param.name+'</td>';
             				content+='  <td>'+param.code+'</td>';
             				content+='  <td>'+param.val+'</td>';
             				content+='  <td>';
             		
             				content+='	  <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/param/edit.htm?pageno='+page.pageno+'&id='+param.id+'\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
             		
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
	        					contentNavigater+='	<li><a href="#" onclick="queryPageParam('+(page.pageno-1)+')">上一页</a></li>';
	        				}
	        				
	        				for ( var i = 1 ; i <= page.totalno ; i++) {
	        					
	        					if(i == page.pageno){	
	        						
	        						contentNavigater += '<li class="active"><a href="#" onclick="queryPageParam('+i+')">'+i+'</a></li>';            						
	        					
	        					} else { 
	        						
	        						contentNavigater += '<li><a href="#" onclick="queryPageParam('+i+')">'+i+'</a></li>';  
	        					
	        					}   						
	        				}
							
	        				if(page.pageno == page.totalno){
	        					contentNavigater+='	<li class="disabled"><a href="#">下一页</a></li>';
	        				}
	        				
	        				if(page.pageno != page.totalno){
	        					contentNavigater+='	<li><a href="#" onclick="queryPageParam('+(page.pageno+1)+')">下一页</a></li>';
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
	        
		
        </script>
        <!-- 提取js脚本 --> 
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
