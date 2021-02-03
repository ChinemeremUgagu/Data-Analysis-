<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Comment</title>
<link rel="stylesheet" href="css/demo.css">
<link rel="stylesheet" href="css/footer-basic-centered.css">

</head>
<body class="center">
    <form method="POST" action='/employee-management/manage?action=save_comment' name="frmstsRpt">
        Compliance Id: <input type="text" name="compId" value="<c:out value="${stsrpt.compId}" />" /><br />
        Comments : <input type="text" name="comments" value="<c:out value="${stsrpt.comments}" />" /><br />
        		   <input type="hidden" name="id" value="<c:out value="${stsrpt.id}" />" /><br />
        		   
        <input type="submit" value="Submit" />
        <input type="submit" value="Cancel" name="cancel"/>
    </form>
   <%@ include file="footer.jsp"%>
</body>
</html>