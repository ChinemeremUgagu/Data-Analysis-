<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Employee</title>
<link rel="stylesheet" href="css/demo.css">
<link rel="stylesheet" href="css/footer-basic-centered.css">

</head>
<body class="center">
    <form method="POST" action='/employee-management/manage?action=save' name="frmEmp">
        First Name : <input type="text" name="fname" value="<c:out value="${user.fname}" />" /><br />
        Last Name : <input type="text" name="lname" value="<c:out value="${user.lname}" />" /><br />
        Email : <input type="text" name="email" value="<c:out value="${user.email}" />" /><br />
        Phone : <input type="text" name="phone" value="<c:out value="${user.phone}" />" /><br />
        Role : <input type="text" name="role" value="<c:out value="${user.role}" />" /><br />
        UserId : <input type="text" name="userid" value="<c:out value="${user.userid}" />" /><br />
        Password : <input type="text" name="password" value="<c:out value="${user.password}" />" /><br />
        		   <input type="hidden" name="id" value="<c:out value="${user.id}" />" /><br />
        <input type="submit" value="Submit" />
        <input type="submit" value="Cancel" name="cancel"/>
    </form>
   <%@ include file="footer.jsp"%>
</body>
</html>