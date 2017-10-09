<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<title>尚筹网-实名认证审核管理</title>
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
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 实名认证审核管理</a></div>
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
				<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">


          <div class="table-responsive">
          	
            <table class="table  table-bordered">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>流程名称</th>
                  <th>流程版本</th>
                  <th>任务名称</th>
                  <th>申请会员</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              
                
              </tbody>
			  <tfoot>
			  	
			     <tr>
				     <td colspan="6" align="center">
						<div id="Pagination" class="pagination">							
							
						</div>
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

            var dataObj = {
        			"pageno" : 1,
        			"pagesize" : 2 
        		};
            function queryPage(pageno){
            	dataObj.pageno = pageno+1;
            	var index = -1 ;
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH }/authCert/queryPage.do",
            		data : dataObj,
            		beforeSend : function(){
            			index = layer.load(2, {time: 10*1000});
            			return true ;
            		},
            		success : function(result){
            			layer.close(index);
            			if(result.success){
            				
            				var page = result.page ;
            				var data = page.data ; //list<Map<String,Object>>
            				
            				var content = '';

							// 如果数据data不为null
							if (data != null) {
								//for(var i = 0 ; i< data.length;i++){}
								$.each(data,function(index,map){
									content+='<tr>';
									content+='  <td>'+(index+1)+'</td>';
									content+='  <td>'+map.processDefName+'</td>';
									content+='  <td>'+map.processDefVersion+'</td>';
									content+='  <td>'+map.taskName+'</td>';
									content+='  <td>'+map.membername+'</td>';
									content+='  <td>';
									content+='	  <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/authCert/show.do?taskid='+map.taskid+'&memberid='+map.memberid+'\'" ><i class=" glyphicon glyphicon-check"></i></button>';
									content+='  </td>';
									content+='</tr>';
								});

								$("tbody").html(content); // innerHTML
							}

							// 否则将给前台友好的提示
							content+='<tr>';
							content+='<td colspan="6" align="center">您暂时还没有任何任务!</td>';
							content+='</tr>';

                         $("tbody").html(content); // innerHTML

            				// 创建分页
            				$("#Pagination").pagination(page.totalsize, {
            					num_edge_entries: 2, //边缘页数.
            					num_display_entries: 4, //主体页数.
            					callback: queryPage, //查询当前页的数据.
            					items_per_page:page.pagesize, //每页显示1项.
            					current_page:(page.pageno-1), //当前页,索引从0开始.
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
            

            
        </script>
		<script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
    