<%-- 
    Document   : cart
    Created on : Nov 2, 2021, 10:09:53 PM
    Author     : Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
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
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>                                           
                        <c:forEach var="dto" items="${items.values()}" varStatus="counter">
                        <form action="cartDeleteItemAction">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>    
                                    ${dto.name}
                                </td>                                   
                                <td>
                                    <button type="button" id="decrease" onclick="descendingQuantity(event)">-</button>
                                    <input type="number" value="${dto.quantity}" min="1" max="${dto.quantity}" name="quantity"/>
                                    <button type="button" id="increase" onclick="ascendingQuantity(event)">+</button>                               
                                </td>
                                <td>
                                    ${dto.price * dto.quantity}
                                </td>
                                <td>
                                    <input type="hidden" name="txtID" value="${dto.id}" />
                                    <input type="submit" value="Remove" name="btnAction" />
                                </td>       
                            </tr>
                        </form>
                    </c:forEach>   
                    <tr>
                        <td colspan="3">                                
                            <a href="storePage">Add more book</a>
                        </td>
                        <td>
                            Total: ${cart.totalPrice}
                        </td>
                    </tr>

                </tbody>
            </table>
            <form action="checkOutAction">
                Full name <input type="text" name="fullname" value="" required=""/>
                Address <input type="text" name="address" value="" required=""/></br>
                <input type="submit" value="Checkout" name="btAction"/>                
            </form>
        </c:if>  
        <c:if test="${empty items}">
            <h2>Nothing in your cart</h2>
        </c:if>
    </c:if>
    <c:if test="${empty cart}">
        <h2>Nothing in your cart</h2>
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
