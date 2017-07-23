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
	<form name="itemsForm" action="${pageContext.request.contextPath}/items/getItems.action" method="post">
		查询条件：
		<table>
			<tr>
				<td><input type="text" placeholder="请输入查询条件" name="itemsCustom.name"></td>
				<td><input type="button" value="查询 " onclick="queryItems()"></td>
				<td><input type="button" value="批量修改提交 " onclick="editItemsAllSubmit()"></td>
			</tr>
		</table>
		<br>
		商品列表：
		<table width=100%>
			<tr>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
			</tr>
			<c:forEach items="${itemsList}" var="item" varStatus="status">
				<input type="hidden" name="itemsList[${status.index}].id" value="${item.id }"/>
				<tr>
					<td><input type="text" name="itemsList[${status.index}].name" value="${item.name}"></td>
					<td><input type="text" name="itemsList[${status.index}].price" value="${item.price}"></td>
					<td><input type="text" name="itemsList[${status.index}].createtime" value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></td>
					<td><input type="text" name="itemsList[${status.index}].detail" value="${item.detail}"></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<script type="text/javascript">
		function editItemsAllSubmit(){
			document.itemsForm.action="${pageContext.request.contextPath}/items/editItemsAllSubmit.action";
			document.itemsForm.submit();
		}
		function queryItems(){
			document.itemsForm.action="${pageContext.request.contextPath}/items/getItems.action";
			document.itemsForm.submit();
		}
	</script>
</body>
</html>