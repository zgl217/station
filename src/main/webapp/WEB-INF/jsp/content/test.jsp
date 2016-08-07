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
</head>
<body>
	<table class="easyui-datagrid" title="Basic DataGrid" style="width:700px;height:250px"
            data-options="singleSelect:true,collapsible:true,url:'${path}/getUserList',method:'get'">
        <thead>
            <tr>
                <th data-options="field:'id',width:80">ID</th>
                <th data-options="field:'name',width:100">NAME</th>
                <th data-options="field:'phone',width:80,align:'right'">PHONE</th>
                <th data-options="field:'age',width:80,align:'right'">AGE</th>
                <th data-options="field:'pass',width:250">PASS</th>
            </tr>
        </thead>
    </table>
</body>
<script type="text/javascript">
 $(function(){
	 $.get("${path}/getUserList",function(data){
		    console.log(data);
			var d = $.parseJSON(data);
			 console.log(d);
		});
 });
</script>
</html>