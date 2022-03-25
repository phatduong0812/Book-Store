<%-- 
    Document   : search
    Created on : Nov 2, 2021, 1:28:36 AM
    Author     : Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER}">
            <font color="red">
        Welcome, ${sessionScope.USER.fullname}
        </font></br>
        </c:if>      
        <c:if test="${not empty sessionScope.USER_GOOGLE}">
            <font color="red">
        Welcome, ${sessionScope.USER_GOOGLE.name}
        </font></br>
        </c:if> 
        <a href="profilePage">Home</a>
        <h1>Search Page</h1>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <form action="searchAction">
            Search: <input type="text" name="txtSearch" 
                           value="${searchValue}" />
            <input type="submit" value="Search" name="btnAction" />
        </form>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                User login by username and password
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountAction">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
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
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" 
                                           value="deleteAccountAction">
                                        <c:param name="pk" 
                                                 value="${dto.username}"/>
                                        <c:param name="lastSearchValue" 
                                                 value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" 
                                           name="btnAction" />
                                    <input type="hidden" name="lastSearchValue"
                                           value="${searchValue}" />
                                </td>                                
                        </form>
                    </c:forEach>
                </tr>
            </tbody>
        </table>

    </c:if>
    <c:set var="resultGG" value="${requestScope.SEARCH_RESULT_GOOGLE}"/>
    <c:if test="${not empty resultGG}">
        User login by Google account
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>ID</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <c:forEach var="dtoGG" items="${resultGG}" varStatus="counter">
                <form action="updateAccountAction">
                    <tr>
                        <td>
                            ${counter.count}
                        </td>
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
                            ${dtoGG.id}
                            <input type="hidden" name="txtID" value="${dtoGG.id}" />
                        </td>
                        <td>
                            <input type="checkbox" name="checkAdmin" 
                                   <c:if test="${dtoGG.role}">
                                       checked="checked"
                                   </c:if>
                                   />
                        </td>
                        <td>
                            <c:url var="urlRewriting" 
                                   value="deleteAccountAction">
                                <c:param name="pk" 
                                         value="${dtoGG.username}"/>
                                <c:param name="lastSearchValue" 
                                         value="${searchValue}"/>
                            </c:url>
                            <a href="${urlRewriting}">Delete</a>
                        </td>
                        <td>
                            <input type="submit" value="Update" 
                                   name="btnAction" />
                            <input type="hidden" name="lastSearchValue"
                                   value="${searchValue}" />
                        </td>                                
                </form>
            </c:forEach>
        </tr>
    </tbody>
</table>

</c:if>
<c:if test="${empty result && empty resultGG}">
    <h2>No result is matched</h2>
</c:if>
</c:if>     

</body>
</html>
