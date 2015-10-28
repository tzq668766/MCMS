<!DOCTYPE html>
<html lang="zh">
 <head>
<#include "/manager/include/macro.ftl"/>
<#include "/manager/include/meta.ftl"/>
</head>
<body>
<@ms.content>
 	<@ms.contentMenu>
		 <!-- 树形模块菜单开始 -->
		<#if listColumn?has_content>
				<@ms.tree  treeId="inputTree" json="${listColumn?default('')}" addNodesName="全部"  jsonId="categoryId" jsonPid="categoryCategoryId" jsonName="categoryTitle"   showIcon="true" expandAll="true" getZtreeId="getZtreeId(event,treeId,treeNode);" />
			<#else> 
				<@ms.nodata content="暂无栏目"/>
		</#if>
		<!-- 树形模块菜单结束 -->
	</@ms.contentMenu>
	<@ms.contentBody width="85%" style="overflow-y: hidden;">
		<@ms.contentPanel  style="margin:0;padding:0;overflow-y: hidden;">
		<iframe src="${base}/manager/cms/article/0/list.do" style="width:100%;maring:0;padding:0;border:none;height:100%;background-image: url(${static}/loading.gif);  background-repeat: no-repeat;  background-position: center;" id="listFrame" target="listFrame" ></iframe>
		</@ms.contentPanel>
	</@ms.contentBody>
</@ms.content>
<script>
//树形结点
function getZtreeId(event,treeId,treeNode){
	if (treeNode.columnType==1) {
		$("#listFrame").attr("src","${base}/manager/cms/article/"+treeNode.categoryId+"/list.do?categoryTitle="+encodeURIComponent(treeNode.categoryTitle));
	} else if(treeNode.columnType==2){
		//判断该单篇栏目是否存在文章
		$.ajax({ 
			type: "POST", 
			url: base+"/manager/cms/article/"+treeNode.categoryId+"/queryColumnArticle.do",
			dataType:"json",
			success: function(msg){
				if (msg.result) {
					$("#listFrame").attr("src","${base}/manager/cms/article/add.do?categoryId="+treeNode.categoryId+"&categoryTitle="+encodeURIComponent(treeNode.categoryTitle));
				} else {
					//如果该单篇栏目下存在文章则跳转到文章编辑页
					$("#listFrame").attr("src","${base}/manager/cms/article/"+treeNode.categoryId+"/edit.do?categoryId="+treeNode.categoryId+"&categoryTitle="+encodeURIComponent(treeNode.categoryTitle));
				}
			},
		});
	} else if(treeNode.columnType=="" || treeNode.columnType == undefined){
		$("#listFrame").attr("src","${base}/manager/cms/article/0/list.do");
	}
}
</script>
</body>
</html>
