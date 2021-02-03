<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Employee Management</title>
<link rel="stylesheet" href="css/demo.css">
<link rel="stylesheet" href="css/footer-basic-centered.css">
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
	border-right: 1px solid #bbb;
}

li:last-child {
	border-right: none;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not (.active ) {
	background-color: #111;
}

.active {
	background-color: #4CAF50;
}
</style>
</head>
<body>
	<h1 class="center">Employee Management</h1>
	<ul>
		<li><a class="active">Welcome ${user.fname} ${user.lname} : ${id}</a></li>
			<c:if test = "${user.role == 'admin'}">
				<li><a href="/employee-management/manage?action=add">Add Employee</a></li>
				<li><a href="/employee-management/manage?action=add_dept">Add Department</a></li>
				<li><a href="/employee-management/manage?action=add_comp">Add Compliance</a></li>
			</c:if>
				<li><a href="/employee-management/manage?action=add_comment">Add Comment</a></li>
		<li style="float: right"><a href="/employee-management/manage?action=logout">Log Out</a></li>
	</ul>
	
	
	<c:if test = "${user.role == 'admin'}">
	
	<table border="1"  class="center">
		<tr><td>Employees</td></tr>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Role</th>
			<th>UserId</th>
		</tr>
		<c:forEach items="${userList}" var="user">
			<tr>
				<%-- <td>${item.id}</td>--%>
				<td>${user.fname}</td>
				<td>${user.lname}</td>
				<td>${user.email}</td>
				<td>${user.phone}</td>
				<td>${user.role}</td>
				<td>${user.userid}</td>
				<td><a href="/employee-management/manage?action=edit&id=<c:out value='${user.id}' />">Edit</a></td>
				<td><a href="/employee-management/manage?action=delete&id=<c:out value='${user.id}' />">Delete</a></td>

			</tr>
		</c:forEach>
	</table>
	
	<table border="1"  class="center">
		<tr><td>Departments</td></tr>
		<tr>
			<th>Department_Id</th>
			<th>Department_Name</th>
		</tr>
		<c:forEach items="${deptList}" var="dept">
			<tr>
				<td>${dept.id}</td>
				<td>${dept.deptName}</td>
				<td><a href="/employee-management/manage?action=edit_dept&id=<c:out value='${dept.id}' />">Edit</a></td>
				<td><a href="/employee-management/manage?action=delete_dept&id=<c:out value='${dept.id}' />">Delete</a></td>

			</tr>
		</c:forEach>
	</table>
	</c:if>
	<table border="1"  class="center">
		<tr><td>Compliance</td></tr>
		<tr>
			<th>Compliance_Id</th>
			<th>Regulation Type</th>
			<th>Details</th>
			<th>Created Date</th>
			<th>Departmen_Id</th>
		</tr>
		<c:forEach items="${compList}" var="comp">
			<tr>
				<td>${comp.id}</td>
				<td>${comp.rltype}</td>
				<td>${comp.details}</td>
				<td><fmt:formatDate value="${comp.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>${comp.departmentId}</td>
				<c:if test = "${user.role == 'admin'}">
					<td><a href="/employee-management/manage?action=edit_comp&id=<c:out value='${comp.id}' />">Edit</a></td>
					<td><a href="/employee-management/manage?action=delete_comp&id=<c:out value='${comp.id}' />">Delete</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>

	
	<table border="1"  class="center">
		<tr><td>Comments</td></tr>
		<tr>
			<th>Comment_Id</th>
			<th>Comment</th>
			<th>Compliance_Id</th>
			<th>Departmen_Id</th>
			<th>Employee_Id</th>
			<th>Created Date</th>
		</tr>
		<c:forEach items="${stsrptList}" var="stsrpt">
			<tr>
				<td>${stsrpt.id}</td>
				<td>${stsrpt.comments}</td>
				<td>${stsrpt.compId}</td>
				<td>${stsrpt.deptId}</td>
				<td>${stsrpt.empId}</td>
				<td><fmt:formatDate value="${stsrpt.createDate}" pattern="yyyy-MM-dd"/></td>
				<c:if test = "${id == stsrpt.empId}">
				<td><a href="/employee-management/manage?action=edit_comment&id=<c:out value='${stsrpt.id}' />">Edit</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	
	
	<%@ include file="footer.jsp"%>
</body>
</html>