//声明方法
function showMenu() {
	//获取当前访问的URL地址
	//http://localhost:8888/atcrowdfunding-main/manager/index.htm
	var url = window.location.href;
	//获取访问的主机域名
	//localhost:8888
	var host = window.location.host;
	//获取项目上下文路径 /atcrowdfunding-main
	var contextPath = "${APP_PATH}";
	//对获取的路径进行截取
	// /atcrowdfunding-main/manager/index.htm?pageno=1
	// url.indexOf(host) 在url中获取host的下标
	var path = url.substring(url.indexOf(host) + host.length);
	//将项目上下文截取掉(获取剩下的部分)
	// /manager/index.htm?pageno=1
	path = path.substring(contextPath.length);
	//将携带的参数也同样进行截取
	//substring(int beginIndex)从指定位置开始截取到末尾(舍弃截取的部分)
	//substring(int beginIndex, int endIndex)从beginIndex截取到endIndex(保留截取的部分)
	//等于-1说明该字符串中不存在该符号
	if(path.indexOf("?")!=-1) {
		//如果存在进行截取
		path = path.substring(0, path.indexOf("?"));
	}
	//在页面中的菜单栏搜索与之相同的路径地址
	var $link = $(".list-group a[href*=\'"+path+"\']");
	//如果查找到该跳转路径则标红
	$link.css("color","red");
	//获取到无序列表ul节点(显示)
	$link.parent().parent().show();
	//去掉父节点中的某个属性
	$link.parent().parent().parent().removeClass("tree-closed");
}

//调用声明的方法
showMenu();