<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>尚筹网-流程管理</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/jquery/pagination_zh/pagination_zh/lib/pagination.css">
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

    <nav class="navbar navbar-inverse navbar-fixed-top" >
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 流程维护</a></div>
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
				<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button id="deleteBatch" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button id="updateProcDef" type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-upload"></i> 上传流程定义文件</button>

<br>
 <hr style="clear:both;">
          <div class="table-responsive">
          		<form id="processDefForm" method="post" enctype="multipart/form-data">
					<input style="display:none;" type="file" class="form-control" id="updateProcessDefFile" name="procDefFile">
				</form>
            <table class="table  table-bordered">
              <thead>
                <tr>
                  <th>序号</th>
				  <th width="30"><input id="checkboxAll" type="checkbox"></th>
                  <th>流程名称</th>
                  <th>流程KEY</th>
                  <th>流程版本</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              
                
              </tbody>
			  <tfoot>
			  	
			     <tr>
				     <td colspan="6" align="center">
						<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
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
	<script src="${APP_PATH}/jquery/pagination_zh/pagination_zh/lib/jquery.pagination.js"></script>
	<script src="${APP_PATH }/jquery/jquery-form/jquery-form.min.js"></script>
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
			    
			    queryPage(0);
			    
			   
            });
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            
            $("#updateProcDef").click(function(){ //传递函数,表示绑定单击事件.
            	$("#updateProcessDefFile").click(); //没传函数,表示执行元素单击操作.
            });
            
            $("#updateProcessDefFile").change(function(){ //如果用户选择上传文件,文件域中发生变化,就提交表单进行文件上传.
            	//$("#processDefForm").submit(); //同步提交表单
            
        			var options = {
            			url:"${APP_PATH}/process/deploy.do",
           				beforeSubmit : function(){
           					loadingIndex = layer.msg('数据正在部署中', {icon: 6});
                   			return true ; //必须返回true,否则,请求终止.
           				},
           				success : function(result){
                			layer.close(loadingIndex);
                			//解决两次部署同一个文件,第二次无法部署的问题
                			//原因是上传一次文件之后, 表单域将该次域中的值设置为默认, 再次上传同一个文件相当于没有发生改变
                			//这时必须将文件域初始化到文本域为空时的状态
                			$("#processDefForm")[0].reset(); 
                			if(result.success){
                				layer.msg("流程部署成功", {time:1000, icon:6} , function(){                					
                    				queryPage(0);
                				});              
                				//layer.msg("流程部署成功", {time:1000, icon:6});
                				//queryPage(0);
                			}else{
                				layer.msg(result.errorMessage, {time:1000, icon:5, shift:6});
                			}	
                		}	
            		};
            		//异步提交表单
            		$("#processDefForm").ajaxSubmit(options); 
            });
            
            
            function pageChange(pageno){
            	//window.location.href="${APP_PATH }/user/index.do?pageno="+pageno;
            	queryPage(pageno);
            }
            
            
            var dataObj = {
        			"pageno" : 1,
        			"pagesize" : 2 
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
            	
            	queryPage(1);
            });
            
            var loadingIndex = -1 ;
            function queryPage(pageno) {
            	dataObj.pageno = pageno+1 ;
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH }/process/doIndex.do",
            		data : dataObj,
            		beforeSend : function() {
            			loadingIndex = layer.msg("正在加载数据...", {time:1000, icon:6});
            			return true ;
            		},
            		success : function(result){
            			if(result.success){
            				layer.close(loadingIndex);
            				var page = result.page ;
            				var data = page.data ; 
            				
            				var content = '';
            				$.each(data, function(index, processDef){
            					content+='<tr>';
                				content+='  <td>'+(index+1)+'</td>';
                				content+='  <td><input class="checkboxClass" type="checkbox" value="'+processDef.id+'"></td>';
                				content+='  <td>'+processDef.name+'</td>';
                				content+='  <td>'+processDef.key+'</td>';
                				content+='  <td>'+processDef.version+'</td>';
                				content+='  <td>';
                				content+='	  <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/process/showImg.htm?id='+processDef.id+'\'" ><i class=" glyphicon glyphicon-check"></i></button>';
                				content+='	  <button type="button" class="btn btn-danger btn-xs" onclick="deleteProcessDef(\''+processDef.id+'\',\''+processDef.name+'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
                				content+='  </td>';
                				content+='</tr>';	
            				});
            				
            				
            				$("tbody").html(content);
            				
            				
            				// 创建分页条
            				$("#Pagination").pagination(page.totalsize, {
            					num_edge_entries: 2, //边缘页数
            					num_display_entries: 4, //主体页数
            					callback: queryPage, //查询当前页的数据.
            					items_per_page:page.pagesize, //每页显示1项
            					current_page:(page.pageno-1), //当前页,索引从0开始
            					prev_text:"上一页",
            					next_text:"下一页"
            				});
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
            
            
            function deleteProcessDef(id,name){
            	
            	layer.confirm("删除["+name+"]流程,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
            		var loadingIndex = -1 ;
            		$.ajax({
            			type:"post",
            			url:"${APP_PATH}/process/doDelete.do",
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
            					layer.msg("流程定义删除成功", {time:1000, icon:6}, function(){
            						queryPage(0);
                				});            					
            				}else{
            					layer.msg(result.errorMessage, {time:1000, icon:5, shift:6});
            				}
            			},
            			error:function(){
            				layer.msg("流程定义删除失败!", {time:1000, icon:5, shift:6});
            			}
            			
            		});
            		
            		
    			    layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            	
            }
            
            $("#checkboxAll").click(function(){
            	
            	var checkboxAllStatus = this.checked ;
            	
            	$(".checkboxClass").each(function(i,n){
            		n.checked = checkboxAllStatus;
            	});
            	
            	/* $.each(array,function(i,n){}); */
            	
            });
            
            $("#deleteBatch").click(function(){
            	var checkBoxChecked = $(".checkboxClass:checked");
            	var length = checkBoxChecked.length;
            	
            	/* var paramStr = "";
            	$.each(checkBoxChecked,function(i,n){
            		if(i!=0){
            			paramStr += "&";
            		}
            		paramStr += "id="+n.value ; // id=1&id=2&id=3
            	}); */
            	
            	
            	/* var dataObj = {};
            	
            	$.each(checkBoxChecked,function(i,n){
            		// <input name="ids[0]" value="1">
            		// <input name="ids[1]" value="2">
            		dataObj['ids['+i+']'] = n.value ; 
            		
            	}); */
            	
            	
				var dataObj = {};
            	
            	$.each(checkBoxChecked,function(i,n){
            		// <input name="ids[0]" value="1">
            		// <input name="ids[1]" value="2">
            		dataObj['datas['+i+'].id'] = n.value ;  //   datas表示封装为Datas类型对象的datas属性名称.
            		dataObj['datas['+i+'].username'] = "aaa" ; 
            	});
            	
            	if(length>0){
            		layer.confirm("删除这些用户,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
                		var loadingIndex = -1 ;
	            		$.ajax({
	            			type:"post",
	            			url:"${APP_PATH}/user/doDeleteBatch.do",
	            			//data:paramStr,
	            			data:dataObj,
	            			beforeSend:function(){
	            				loadingIndex = layer.msg("正在删除数据!", {time:1000, icon:6});
	            				return true ;
	            			},
	            			success:function(result){
	            				layer.close(loadingIndex);
	            				if(result.success){
	            					queryPageUser(1);
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
    