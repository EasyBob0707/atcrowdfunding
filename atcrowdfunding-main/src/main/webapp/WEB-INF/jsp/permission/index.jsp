<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <title>尚筹网-许可维护</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
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
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 权限菜单列表 </h3>
			  </div>
			  <div class="panel-body">
				  <!-- 权限树在treeDemo区域中进行初始化  -->
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
	<!-- 不导入该js文件效果的没有的 -->
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>

    <script type="text/javascript">

        //控制面板的展示的js控制(页面尾部就默认加载完页面就进行加载了)
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

		var setting = {	
				
				view : {
					
					//是否可以多选
					selectedMulti: false,
					
					//更换图标
					addDiyDom: function(treeId, treeNode){
						var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
						if ( treeNode.icon ) {
							icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
						}
					},
					
					//加入悬浮按钮(如: 增/删/改/查)
					addHoverDom: function(treeId, treeNode){  
						var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
						//取消href事件(可以省略;)
						aObj.attr("href", "javascript:;");
						if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
						var s = '<span id="btnGroup'+treeNode.tId+'">';
						if ( treeNode.level == 0 ) {
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" title="添加权限信息" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
						} else if ( treeNode.level == 1 ) {
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg " onclick="window.location.href=\'${APP_PATH}/permission/toEdit.htm?id='+treeNode.id+'\'"></i></a>';
							if (treeNode.children.length == 0) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" title="删除权限信息" onclick="deletePermission('+treeNode.id+',\''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" title="添加权限信息" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
						} else if ( treeNode.level == 2 ) {
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg " onclick="window.location.href=\'${APP_PATH}/permission/toEdit.htm?id='+treeNode.id+'\'"></i></a>';
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" title="删除权限信息" onclick="deletePermission('+treeNode.id+',\''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
						}
		
						s += '</span>';
						aObj.after(s);
					},
					
					removeHoverDom: function(treeId, treeNode){
						$("#btnGroup"+treeNode.tId).remove();
					}
				},
				async: {
			        enable: true,
			        //url:"${APP_PATH}/permission/loadData.do", //返回result对象,封装许可数据,对于同步方式允许,但是,异步加载树是不允许的.
			        url:"${APP_PATH}/permission/asyncLoadData.do", //异步加载许可树
			        autoParam:["id", "name=n", "level=lv"] //将节点id属性name,level值传递给后台.如果参数名需要改变,可以使用"name=别名"来修改参数.
			   }
		};
		
		//异步
		$.fn.zTree.init($("#treeDemo"), setting);
		
		
		function deletePermission(id,name){
			
			layer.confirm("删除["+name+"]许可,确认删除?",  {icon: 3, title:'提示'}, function(cindex){
        		var loadingIndex = -1 ;
        		$.ajax({
        			type:"post",
        			url:"${APP_PATH}/permission/doDelete.do",
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
        					layer.msg("许可删除成功", {time:1000, icon:6}, function(){
        						//删除成功后,刷新许可树
        						//读取当前树对象
        						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        						//刷新当前树对象的数据
        						//第一个参数如果为null,表示刷新整棵树,否则,可以设置节点对象,对该节点进行刷新
        						treeObj.reAsyncChildNodes(null, "refresh"); 

            				});            					
        				}else{
        					layer.msg("许可删除失败!", {time:1000, icon:5, shift:6});
        				}
        			},
        			error:function(){
        				layer.msg("许可删除失败!", {time:1000, icon:5, shift:6});
        			}
        			
        		});
        		
        		
			    layer.close(cindex);
			}, function(cindex){
			    layer.close(cindex);
			});
			
		}
		
		/* 数据是固定的, 只做显示用, 正真的数据是从数据库查询获取的  */
		/* 
		   后期数据是从数据库中获取的, 需要创建一个对应的类型将数据封装到该对象到那个中
		   用AjaxResult中的data属性将数据进行返回
		   后台封装的对象属性必须与前台(ztree)中封装的属性名称一致
		   否则后台对其操作不起作用, 后台传入的字段信息要与前台接收的字段对应
		*/
		/* 
		var loadingIndex = -1;
		$.ajax({
			type : "POST",
			url : "${APP_PATH}/permission/toLoadingData.do",
			beforeSend : function(){
				loadingIndex = layer.msg("加载数据中...", {time:1000, icon:6});
				return true;
			},
			success : function(result){
				layer.close(loadingIndex);
				if(result.success){			
					$.fn.zTree.init($("#treeDemo"), setting, result.data);
				} else {
					layer.msg(result.errorMessage, {time:2000, icon:5, shift:6});
				}
			},
			error : function(){
				layer.msg(result.errorMessage, {time:2000, icon:5, shift:6});
			}
		});
		 */
	</script>
    <!-- 提取js脚本 --> 
    <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
