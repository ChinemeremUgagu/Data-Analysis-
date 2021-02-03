<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Department</title>
<link rel="stylesheet" href="css/demo.css">
<link rel="stylesheet" href="css/footer-basic-centered.css">

</head>
<body class="center">
    <form method="POST" action='/employee-management/manage?action=save_dept' name="frmDept">
        Department Name : <input type="text" name="dept_name" value="<c:out value="${dept.deptName}" />" /><br />
        		   		  <input type="hidden" name="id" value="<c:out value="${dept.id}" />" /><br />
        <input type="submit" value="Submit" />
        <input type="submit" value="Cancel" name="cancel"/>
    </form>
   <%@ include file="footer.jsp"%>
</body>
</html>