<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉信息管理</title>
<link rel="stylesheet" type="text/css" href="${path }/static/jquery-easyui-1.4.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/static/jquery-easyui-1.4.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path }/static/style/index.css">
<script type="text/javascript" src="${path }/static/script/jquery.min.js"></script>
<script type="text/javascript" src="${path }/static/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path }/static/script/app_index.js"></script>
</head>
<body>
	<div style="position:absolute;width:100%;height:100%;">
		<div id="wrap" class="easyui-layout" style="width:100%;height:100%;">
	        <div data-options="region:'north'" style="height:50px;line-height:46px;color:#fff;text-indent:50px;background: rgb(51, 143, 204)">
	        	<span style="letter-spacing:5px;font-size:26px">投诉信息管理系统</span>
	        </div>
	        <div data-options="region:'south'" style="text-align:center;height:30px;line-height:28px;color:#fff;background: rgb(51, 143, 204)">
	        	© 2016
	        </div>
	        <div data-options="region:'west',split:true" title="菜单" style="width:150px;">
	        	<jsp:include page="/WEB-INF/jsp/layout/menu.jsp"/>
	        </div>
	        <div id="frameBox" data-options="region:'center'" >
	        	<div id="index_tabs" class="easyui-tabs" style="width:100%;height:100%;border:false">
					<div title="关联热点查询"  data-options="fit:true" >
			        	<iframe id="conFrame" src="${path }/hot" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" style="width:100%;height:97%" ></iframe>
					</div>
				</div>
	        </div>
	    </div>
	</div>
</body>
</html>