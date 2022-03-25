<%-- 
    Document   : store
    Created on : Nov 2, 2021, 8:09:15 PM
    Author     : Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Page</title>
    </head>
    <style>
        input[type=number]::-webkit-inner-spin-button,
        input[type=number]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            margin: 0;
        }
    </style>
    <body>
        <h1>Store Page</h1>
        <c:set var="listProduct" value="${sessionScope.LIST_PRODUCT}"/>
        <c:if test="${not empty listProduct}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Add</th>
                    </tr>
                </thead>
                <tbody>                      
                    <c:forEach var="dto" items="${listProduct}" varStatus="counter">
                    <form action="cartAddItem">
                        <tr> 
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                                <input type="hidden" name="txtName" value="${dto.name}" />
                            </td>
                            <td>
                                ${dto.price}
                                <input type="hidden" name="price" value="${dto.price}" />
                            </td>
                            <td>
                                <button type="button" id="decrease" onclick="descendingQuantity(event)">-</button>
                                <input type="number" value="1" min="1" max="${dto.quantity}" name="quantity" style="width: 44%;"/>
                                <button type="button" id="increase" onclick="ascendingQuantity(event)">+</button>                               
                            </td>
                            <td>
                                <input type="hidden" name="txtID" value="${dto.id}" />
                                <input type="submit" value="Add to cart" name="btnAction" />                                
                            </td>
                        </tr>
                    </form>
                </c:forEach>                    
                <form action="cartPage">
                    <input type="submit" value="View cart" name="btnAction" />
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty listProduct}">
        No product
    </c:if>
    <script>


        function descendingQuantity(event) {
            let textFiled = event.target.nextElementSibling.value;
            console.log(textFiled)
            if (parseInt(textFiled) > 1) {
                textFiled = parseInt(textFiled) - 1;
                event.target.nextElementSibling.value = textFiled;
            }
        }

        function ascendingQuantity(event) {
            let textFiled = event.target.previousElementSibling.value;
            textFiled = parseInt(textFiled) + 1;
            event.target.previousElementSibling.value = textFiled;
        }

    </script>
</body>
</html>
