<%-- 
    Document   : register
    Created on : Nov 2, 2021, 6:15:24 PM
    Author     : Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register Page</h1>
        <form action="registerAction">
            Username: <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /></br>
            <c:if test="${not empty requestScope.REGISTER_ERROR.usernameLengthError}">
                <font color="red">
                ${requestScope.REGISTER_ERROR.usernameLengthError}
                </font></br>
            </c:if>
            <c:if test="${not empty requestScope.REGISTER_ERROR.usernameIsExisted}">
                <font color="red">
                ${requestScope.REGISTER_ERROR.usernameIsExisted}
                </font></br>
            </c:if>
                Password: <input type="password" name="txtPassword" value="" /></br>
            <c:if test="${not empty requestScope.REGISTER_ERROR.passwordLengthError}">
                <font color="red">
                ${requestScope.REGISTER_ERROR.passwordLengthError}
                </font></br>
            </c:if>
                Confirm password: <input type="password" name="txtConfirm" value="" /></br>
            <c:if test="${not empty requestScope.REGISTER_ERROR.confirmNotMatchError}">
                <font color="red">
                ${requestScope.REGISTER_ERROR.confirmNotMatchError}
                </font></br>
            </c:if>
            Full Name: <input type="text" name="txtFullName"
                              value="${param.txtFullName}" /></br>
            <c:if test="${not empty requestScope.REGISTER_ERROR.fullnameLengthError}">
                <font color="red">
                ${requestScope.REGISTER_ERROR.fullnameLengthError}
                </font></br>
            </c:if>
            <input type="submit" value="Register" name="btnAction" />
        </form>
    </body>
</html>
