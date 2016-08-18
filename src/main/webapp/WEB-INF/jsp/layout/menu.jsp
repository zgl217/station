<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	ul.menuUl{margin:0;padding:0;padding-top:5px;}
	.menuUl li{list-style:none;text-indent:20px;height:30px;line-height:30px;margin:5px auto;cursor:pointer;width:92%;border-radius:5px;}
	.menuUl li.active{background:#e8e8e8;font-weight:bold;color:blue}
	.menuUl li:hover{background:#e8e8e8;}
</style>
<script type="text/javascript">
$(function(){
	$(".menuUl li").click(function(){
		$(".menuUl li").removeClass("active");
		$(this).addClass("active");
		var url = "${path}"+$(this).attr("url");
		indexTabsAddTab("iframe",{title:$(this).text(),url:url});
// 		$("#conFrame").attr("src",url);
	});
});
</script>
</head>
<body>
		<div style="width:100%;height:100%;background:#EFF2FE">
			<ul class="menuUl">
				<li class="active" url="/community">关联热点查询</li>
				<li url="/trans">附近基站查询</li>
				<li url="/address">地址库录入</li>
			</ul>
	    </div>
</body>
</html>