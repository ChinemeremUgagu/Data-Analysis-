<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Compliance</title>
<link rel="stylesheet" href="css/demo.css">
<link rel="stylesheet" href="css/footer-basic-centered.css">

</head>
<body class="center">
    <form method="POST" action='/employee-management/manage?action=save_comp' name="frmComp">
        Regulation Type : <input type="text" name="rltype" value="<c:out value="${comp.rltype}" />" /><br />
        Details : <input type="text" name="details" value="<c:out value="${comp.details}" />" /><br />
        DepartmentId : <input type="text" name="deptid" value="<c:out value="${comp.departmentId}" />" /><br />
        		   <input type="hidden" name="id" value="<c:out value="${comp.id}" />" /><br />
        <input type="submit" value="Submit" />
        <input type="submit" value="Cancel" name="cancel"/>
    </form>
   <%@ include file="footer.jsp"%>
</body>
</html>