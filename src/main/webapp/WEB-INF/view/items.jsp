<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>items</title>
</head>
<body>
当前用户：${username}
<c:if test="${username!=null}">
	<a href="${pageContext.request.contextPath}/logout.action">注销</a>
</c:if>
	<form name="itemsForm" action="${pageContext.request.contextPath}/items/getItems.action" method="post">
		查询条件：
		<table>
			<tr>
				<td>
					<select name="itemtype">
						<c:forEach items="${itemtypes}" var="itemtype">
							<option value="${itemtype.key}">${itemtype.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>商品的名称：<input type="text" placeholder="请输入查询条件" name="itemsCustom.name"></td>
				<td><input type="button" value="查询 " onclick="queryItems()"></td>
				<td><input type="button" value="批量删除 " onclick="deleteItems()"></td>
				<td><input type="button" value="批量修改提交" onclick="editItemsQuery()"></td>
				<td><input type="button" value="测试异常" onclick="testException()"></td>
			</tr>
		</table>
		<br>
		商品列表：
		<table width=100%>
			<tr>
				<td>选择</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${itemsList}" var="item">
				<tr>
					<td><input type="checkbox" name="ids" value="${item.id}"></td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${item.detail}</td>
					<td><a href="${pageContext.request.contextPath}/items/editItem.action?id=${item.id}">修改</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<script type="text/javascript">
		function deleteItems(){
			document.itemsForm.action="${pageContext.request.contextPath}/items/deleteItems.action";
			document.itemsForm.submit();
		}
		function queryItems(){
			document.itemsForm.action="${pageContext.request.contextPath}/items/getItems.action";
			document.itemsForm.submit();
		}
		function editItemsQuery(){
			document.itemsForm.action="${pageContext.request.contextPath}/items/editItemsQuery.action";
			document.itemsForm.submit();
		}
		function testException(){
			document.itemsForm.action="${pageContext.request.contextPath}/items/testException.action";
			document.itemsForm.submit();
		}
	</script>
</body>
</html>