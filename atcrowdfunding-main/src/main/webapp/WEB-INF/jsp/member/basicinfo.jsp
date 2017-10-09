<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
  </head>
  <body>
 <div class="navbar-wrapper">
      <div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			  <div class="container">
				<div class="navbar-header">
				  <a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
				</div>
            <div id="navbar" class="navbar-collapse collapse" style="float:right;">
            	<jsp:include page="/WEB-INF/jsp/common/memberinfo.jsp"/>
            </div>
			  </div>
			</nav>

      </div>
    </div>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" class="active"><a href="#"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation"><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation"><a href="#"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form role="form" style="margin-top:20px;">
		  <div class="form-group">
			<label for="frealname">真实名称</label>
			<input type="text" class="form-control" id="frealname" placeholder="请输入真实名称">
		  </div>
		  <div class="form-group">
			<label for="fcardnum">身份证号码</label>
			<input type="text" class="form-control" id="fcardnum" placeholder="请输入身份证号码">
		  </div>
		  <div class="form-group">
			<label for="ftel">手机号码</label>
			<input type="text" class="form-control" id="ftel" placeholder="请输入电话号码">
		  </div>
          <button type="button" onclick="window.location.href='${APP_PATH}/member/acctType.htm'" class="btn btn-default">上一步</button>
		  <button type="button" id="nextBtn"  class="btn btn-success">下一步</button>
		</form>
		<hr>
    </div> <!-- /container -->
        <div class="container" style="margin-top:20px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div id="footer">
                        <div class="footerNav">
                             <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                        </div>
                        <div class="copyRight">
                            Copyright ?2017-2017 atguigu.com 版权所有
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script>
        $('#myTab a').click(function (e) {
          e.preventDefault();
          $(this).tab('show');
        });   
        
        $("#nextBtn").click(function(){
        	
        	//表单验证暂时注释(身份证号以及手机号的正则表达式待修改)
        	/* 
        	//验证真实姓名不能为空
			var $frealname = $("#frealname");
			if($.trim($frealname.val()) == "") {
				layer.msg("会员真实姓名不能为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$frealname.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
			
			//验证身份证号不能为空
			var $fcardnum = $("#fcardnum");
			if($.trim($fcardnum.val()) == "") {
				layer.msg("身份证号不能为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$fcardnum.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
		
			//验证身份证号是否符合规则
			var fcardnum_reg = ^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$;
			if(!fcardnum_reg.test($fcardnum.val())){
				layer.msg("身份证号不符合规则!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$fcardnum.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
			
			//验证手机号不能为空
			var $ftel = $("#ftel");
			if($.trim($ftel.val()) == "") {
				layer.msg("手机号不能为空!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$ftel.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
		
			//验证手机号是否符合规则
			var ftel_reg = ^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$;
			if(!ftel_reg.test($ftel.val())){
				layer.msg("手机号不符合规则!", {time:2000, icon:5, shift:6}, function(){
					//获取焦点
					$ftel.focus();
	    			//return ; 只是返回当前回调函数，单击事件函数并没有返回，代码继续往下执行
	    		}); //弹出时间，图标，特效
				return;
			}
        	 */
        	 
       		$.ajax({
       			type : "POST",
       			url : "${APP_PATH}/member/updateBasicInfo.do",
       			data : {
       				realname : $("#frealname").val(),
       				cardnum : $("#fcardnum").val(),
       				tel : $("#ftel").val()
       			},
           		success : function(result){
           			if(result.success){
           				window.location.href="${APP_PATH}/member/uploadFile.htm";
           			}else{
           				layer.msg("操作失败!", {time:1000, icon:5, shift:6});
           			}
           		}
       			
       		});
        	
        });
        
	</script>
  </body>
</html>