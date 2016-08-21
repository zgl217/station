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
	parent.$.messager.progress("close");
// 	$("body").height(parent.document.getElementById("frameBox").style.height);
	$(".content").height($(window).height()-$(".title").height()-$(".filter").height());
	//加载列表
	$("#transList").datagrid(
			{
				url:'${path}/getTrans?address=${address}&bdLng=${param.bdLng}&bdLat=${param.bdLat}&gcLng=${param.gcLng}&gcLat=${param.gcLat}',
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
					{field : 'complain',title : '投诉点',width : 50},
					{field : 'lteCell',title : '最近4G小区',width : 80},
					{field : 'lteDistance',title : '距离(米)',width : 30},
					{field : 'gsmCell',title : '最近2G小区',width : 80},
					{field : 'gsmDistance',title : '距离(米)',width : 30}
				             ] ]
			});
});


</script>
</head>
<body>
	<div class="filter" style="text-align:center;width:100%;height:60px;line-height:60px;border-bottom:1px solid #e0e0e0;background:#f8f8f8">
		<input type="hidden" name="commnuityKey" id="commnuityKey" value="${address}"/>
		<span style="font-size:22px;color:blue">${address}&nbsp;&nbsp;附近基站</span>
    </div>
    <div class="content">
    	<table id="transList"></table>
    </div>
</body>
</html>