<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${path }/static/jquery-easyui-1.4.3/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/static/jquery-easyui-1.4.3/themes/icon.css">
<script type="text/javascript" src="${path }/static/script/jquery.min.js"></script>
<script type="text/javascript" src="${path }/static/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/static/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<style type="text/css">
	body{font-size:12px;width:100%;height:100%}
	.content{overflow-x:hidden;overflow-y:auto}
	/*datagrid*/
    .datagrid-header {background:#EBEFEF;}
    .datagrid-htable {background:#EBEFEF;font-weight:bold}
	.datagrid-header-row,.datagrid-row{ height:34px;}
</style>
<script type="text/javascript">
$(function(){
// 	$("body").height(parent.document.getElementById("frameBox").style.height);
	$(".content").height($(window).height()-$(".title").height()-$(".filter").height());
	//回车提交
	$('#commnuityKey').keydown(function(e){
	    if(e.keyCode == 13){
	    	doSearch();
	    }
	});
	
	//加载列表
	$("#addressList").datagrid(
			{
				url:'${path}/getAddress',
				method : 'get',
				pagination : true,
				rownumbers:true,
				autoRowHeight:true,
				pageSize:50,
				pageList:[20,50,100,200,300],
				fitColumns : true,
				striped: true, //行背景交换
	            nowrap: false, //列内容多时自动折至第二行
	            resize : true,
				remoteSort : true,
				singleSelect:true,
				border:false,	
				height:$(".content").height(), 
				columns : [ [
					{field : 'id',hidden:true},         
					{field : 'area',title : '区县',width : 30},
					{field : 'address',title : '地址',width : 50},
					{field : 'bd_lng',title : '百度经度',width : 50},
					{field : 'bd_lat',title : '百度纬度',width : 50},
					{field : 'gc_lng',title : '谷歌经度',width : 50},
					{field : 'gc_lat',title : '谷歌纬度',width : 50}
				             ] ]
			});
});
function doSearch(){
	var commnuityKey  = $("input[name='commnuityKey']").val();
	$('#addressList').datagrid({
		 url : '${path}/getAddress',queryParams : {complain:commnuityKey}
	});
}
//新增
function doAdd(){
	$("#addDialog").dialog("open");
}
//保存
function doSave(){
	var area  = $("input[name='area']").val();
	if(area==""){
		alert("请输入县区!");
		return
	}
	var address  = $("input[name='address']").val();
	if(address==""){
		alert("请输入地址!");
		return
	}
	var bdjw  = $("input[name='bdjw']").val();
	if(bdjw==""){
		alert("请输入百度经纬度!");
		return
	}else if(bdjw.indexOf(",")==-1){
		alert("百度经纬度格式不正确，请用英文逗号分隔!");
		return
	}
	top.$.messager.progress({text:'数据处理中...'});
	$.post("${path}/saveAddress",{area:area,address:address,bdjw:bdjw},function(data){
		top.$.messager.progress('close');
		var d = $.parseJSON(data);
		if(d.returnCode=="SUCCESS"){
			$("#commnuityKey").val($("input[name='address']").val());
			$("#addDialog").dialog("close");
			doSearch();
		}else{
			alert(d.message);
		}
	});
}
</script>
</head>
<body>
	<div class="filter" style="width:100%;height:60px;line-height:60px;border-bottom:1px solid #e0e0e0;background:#f8f8f8">
		<input type="text" name="commnuityKey" id="commnuityKey"  style="width:300px;height:30px;margin-left:30px;border:1px solid #c0c0c0;border-radius:5px;text-indent:5px;" placeholder="输入小区关键字"/>
		<a class="easyui-linkbutton" onClick="doSearch()">查&nbsp;询</a>
		<a class="easyui-linkbutton" onClick="doAdd()">新&nbsp;增</a>
    </div>
    <div class="content">
    	<table id="addressList"></table>
    </div>
    <!-- dialog -->
    <div id="addDialog" class="easyui-dialog" title="新增地址库" style="width:500px;height:280px" closed="true">
    	<ul>
    		<li>县区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="area" class="addtext"/></li>
    		<li>地址&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="address" class="addtext"/></li>
    		<li>百度经纬度&nbsp;&nbsp;<input name="bdjw" class="addtext"/></li>
    		<li style="text-align:center"><a class="easyui-linkbutton" onClick="doSave()">保&nbsp;存</a></li>
    	</ul>
    </div>
</body>
</html>