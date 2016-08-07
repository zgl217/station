<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${path }/static/jquery-easyui-1.4.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/static/jquery-easyui-1.4.3/themes/icon.css">
<script type="text/javascript" src="${path }/static/script/jquery.min.js"></script>
<script type="text/javascript" src="${path }/static/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	body{font-size:12px;width:100%;height:100%}
	.content{overflow-x:hidden;overflow-y:auto}
	table{width:100%;font-size:12px;}
	table th{height:30px;background:#CFCFFF;border-bottom:3px solid #fff}
	table td{height:30px;background:#f5f5f5;border-bottom:3px solid #fff}
	li{list-style:none;padding:5px;margin:8px auto;cursor:pointer;width:98%;}
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
});
function doSearch(){
	var commnuityKey  = $("input[name='commnuityKey']").val();
	if(commnuityKey==""){
		alert("请输入小区名称或关键字!");
		return
	}
	top.$.messager.progress({text:'数据处理中...'});
	$.get("${path}/getTrans",{commnuityKey:commnuityKey},function(data){
		top.$.messager.progress('close');
		var d = $.parseJSON(data);
		if(d.returnCode=="SUCCESS"){
			var complain = d.complain;
			var list = d.topList;
	 		var html = "";
	 		if(list.length>0){
	 			for(var i=0;i<list.length;i++){
	 				var t = list[i];
	 				html+="<tr><td align='center'>"+complain.complainName+"</td><td align='center'>"+t.lteCell+"</td><td align='center'>"+t.lteDistance+"</td><td align='center'>"+t.gsmCell+"</td><td align='center'>"+t.gsmDistance+"</td></tr>";
	 			}
	 		}else{
	 			html+="<tr><td colspan='5' style='color:red;font-weight:bold;text-align:center;' >未查到数据!</td></tr>";
	 		}
	 		$(".content table tbody").html(html);
		}else{
			$(".content table tbody").html("");
			alert(d.message);
		}
	});
}
</script>
</head>
<body>
	<div class="filter" style="width:100%;height:60px;line-height:60px;border-bottom:1px solid #e0e0e0;background:#f8f8f8">
		<input type="text" name="commnuityKey" id="commnuityKey" style="width:300px;height:30px;margin-left:30px;border:1px solid #c0c0c0;border-radius:5px;text-indent:5px;" placeholder="输入关键字或经纬度查询"/>
		<a id="doSearchBtn" class="easyui-linkbutton" onClick="doSearch()">查&nbsp;询</a>
    </div>
    <div class="content">
    	<table border="0" cellspacing="0" cellpadding="0">
    		<thead>
    			<tr>
    				<th>投诉点</th>
    				<th>最近4G小区</th>
    				<th>距离(米)</th>
    				<th>最近2G小区</th>
    				<th>距离(米)</th>
    			</tr>
    		</thead>
    		<tbody>
    		</tbody>
    	</table>
    </div>
</body>
</html>