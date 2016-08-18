/**
 * 通用布局构造JS，布局命名与中介机构系统index页一致
 */
var index_tabs;

/**
 * 主标签页添加标签项
 * 使用方法：indexTabsAddTab('[href][iframe]',{title:'',url:'',iconCls:''});
 */
var indexTabsAddTab;
var closeTab;
var refreshTab;

$(function() {
	
	index_tabs = $('#index_tabs');
	index_tabs.tabs(
	{
		fit : true,
		border : false,
		tools : [
				{
					iconCls : 'icon-reload',
					handler : function() {
						var tab = index_tabs.tabs('getSelected');
						if (tab) {
							var href = tab.panel('options').href;
							if (href) {/* 说明tab是以href方式引入的目标页面 */
								var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
								index_tabs.tabs('getTab', index).panel('refresh');
							} else {/* 说明tab是以content方式引入的目标页面 */
								var panel = index_tabs.tabs(
										'getSelected').panel(
										'panel');
								var frame = panel
										.find('iframe');
								try {
									if (frame.length > 0) {
										for ( var i = 0; i < frame.length; i++) {
											frame[i].contentWindow.document
													.write('');
											frame[i].contentWindow
													.close();
											frame[i].src = frame[i].src;
										}
										if (navigator.userAgent
												.indexOf("MSIE") > 0) {// IE特有回收内存方法
											try {
												CollectGarbage();
											} catch (e) {
											}
										}
									}
								} catch (e) {
								}
							}
						}
					}
				},
				{
					iconCls : 'icon-no',
					handler : function() {
						var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
						var tab = index_tabs.tabs('getTab',index);
//						alert(tab.panel('options').title);
//						return;
						if (tab) {
							if (tab.panel('options').closable) {
								index_tabs.tabs('close', index);
							} else {
								$.messager.alert('提示','[' + tab.panel('options').title + ']不可以被关闭！', 'error');
							}
						}
					}
				} 
			]
	});

	/*
	 * tab页面跳转 
	 */
	indexTabsAddTab = function(type, params) {
		index_tabs = window.top.$('iframe').parents('#index_tabs');
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		var opts;
		if (type == '' || type == 'href') {
			//alert('href title:'+params.title);
			opts = {
				title : params.title,
				closable : true,
				iconCls : params.iconCls,
				href : params.url,
				border : false,
				fit : true
			};
			//alert(params.url);
		} else {
			//alert('iframe title:'+params.title);
			var iframe = '<iframe src="' + params.url + '" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" style="width:100%;height:100%;"></iframe>';
			opts = {
				title : params.title,
				closable : true,
				iconCls : params.iconCls,
				content : iframe,
				border : false,
				fit : true
			};
		}
		if (index_tabs.tabs('exists', opts.title)) {
//			alert('exists:'+opts.title);
			
			index_tabs.tabs('select', opts.title);
			var tab = index_tabs.tabs('getSelected');
			index_tabs.tabs('update', {
				tab: tab,
				options: opts
			});
			$.messager.progress('close');
		} else {
//			alert('not exists:'+opts.title);
			index_tabs.tabs('add', opts);
			$.messager.progress('close');
		}
	}
	
	/*
	 * 关闭tab(iframe页面用这个方法)
	 */
	closeTab = function(subtitle){
		iframe_tabs = window.top.$('iframe').parents('#index_tabs');
		iframe_tabs.tabs('close', subtitle);
	}
	
	/*
	 * 刷新tab(iframe页面用这个方法)
	 */
	refreshTab = function(){
		iframe_tabs = window.top.$('iframe').parents('#index_tabs');
		var index = iframe_tabs.tabs('getTabIndex',iframe_tabs.tabs('getSelected'));
		iframe_tabs.tabs('getTab', index).panel('refresh');
	}
	
});