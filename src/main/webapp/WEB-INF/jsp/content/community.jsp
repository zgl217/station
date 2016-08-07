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
	$("#communityList").datagrid(
			{
				url:'${path}/getCommunity',
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
					{field : 'complain',title : '投诉点',width : 50},
					{field : 'solution',title : '解决方案描述',width : 300 }
				             ] ]
			});
});

/** 查询  **/
function doSearch(){
	var commnuityKey  = $("input[name='commnuityKey']").val();
	$('#communityList').datagrid({
		 url : '${path}/getCommunity',queryParams : {complain:commnuityKey}
	});
}

</script>
</head>
<body>
	<div class="filter" style="width:100%;height:60px;line-height:60px;border-bottom:1px solid #e0e0e0;background:#f8f8f8">
		<input type="text" name="commnuityKey" id="commnuityKey" style="width:300px;height:34px;margin-left:15px;border:1px solid #c0c0c0;border-radius:5px;text-indent:5px;" placeholder="输入关键字回车查询"/>
		&nbsp;<a class="easyui-linkbutton" onClick="doSearch()">&nbsp;查&nbsp;询&nbsp;</a>
    </div>
    <div class="content">
    	<table id="communityList"></table>
    </div>
</body>
</html>