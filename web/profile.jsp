<%-- 
    Document   : profile
    Created on : Nov 2, 2021, 12:42:33 AM
    Author     : Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
    </head>
    <body>
        <c:set var="dto" value="${sessionScope.USER}"/>
        <c:if test="${not empty dto}">
            <font color="red">
            Welcome, ${dto.fullname}
            </font>
            <form action="logoutAction">
                <input type="submit" value="Logout" name="btnAction" />
            </form>
            <h2>Your profile</h2>        
            <table border="1">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Full Name</th>
                        <th>Role</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                <form action="profileUpdateAction">
                    <tr>
                        <td>
                            ${dto.username}
                            <input type="hidden" name="txtUsername" 
                                   value="${dto.username}" />
                        </td>
                        <td>
                            <input type="text" name="txtPassword"
                                   value="${dto.password}" />
                            <c:if test="${not empty requestScope.UPDATE_ERROR.passwordLengthError and requestScope.UPDATE_ERROR_PK eq dto.username}">
                                </br>
                                <font color="red">
                                ${requestScope.UPDATE_ERROR.passwordLengthError}
                                </font>
                            </c:if>
                        </td>
                        <td>
                            <input type="text" name="txtFullName" 
                                   value="${dto.fullname}" />
                            <c:if test="${not empty requestScope.UPDATE_ERROR.fullnameLengthError and requestScope.UPDATE_ERROR_PK eq dto.username}">
                                </br>
                                <font color="red">
                                ${requestScope.UPDATE_ERROR.fullnameLengthError}
                                </font>
                            </c:if>
                        </td>
                        <td>
                            <input type="checkbox" name="checkAdmin" 
                                   disabled="disabled" 
                                   <c:if test="${dto.role}">
                                       checked="checked"
                                   </c:if>  
                                   />
                        </td>
                        <td>
                            <input type="submit" value="Update" 
                                   name="btnAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
        <c:if test="${dto.role}">
            <c:url var="urlRewriting" value="searchAction"/>
            <a href="${urlRewriting}">Click to search</a>
        </c:if> 
    </c:if>
    <c:set var="dtoGG" value="${sessionScope.USER_GOOGLE}"/>
    <c:if test="${not empty dtoGG}">
        <font color="red">
        Welcome, ${dtoGG.name}
        </font>
        <form action="logoutAction">
            <input type="submit" value="Logout" name="btnAction" />
        </form>
        <h2>Your profile</h2>        
        <table border="1">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>ID</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
            <form action="profileUpdateAction">
                <tr>
                    <td>
                        ${dtoGG.username}
                    </td>
                    <td>
                        ${dtoGG.email}
                    </td>
                    <td>
                        <input type="text" name="txtName" 
                               value="${dtoGG.name}" />
                        <c:if test="${not empty requestScope.UPDATE_ERROR.fullnameLengthError and requestScope.UPDATE_ERROR_PK eq dtoGG.id}">
                            </br>
                            <font color="red">
                            ${requestScope.UPDATE_ERROR.fullnameLengthError}
                            </font>
                        </c:if>
                    </td>
                    <td>
                        <input type="checkbox" name="checkAdmin" 
                               disabled="disabled" 
                               <c:if test="${dtoGG.role}">
                                   checked="checked"
                               </c:if>  
                               />
                    </td>
                    <td>
                        ${dtoGG.id}
                        <input type="hidden" name="txtID" value="${dtoGG.id}" />
                    </td>
                    <td>
                        <input type="submit" value="Update" 
                               name="btnAction" />
                    </td>
                </tr>
            </form>
        </tbody>
    </table>
    <c:if test="${dtoGG.role}">
        <c:url var="urlRewriting" value="searchAction"/>
        <a href="${urlRewriting}">Click to search</a>
    </c:if> 
</c:if>

</body>
</html>
