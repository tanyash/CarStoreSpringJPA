<%@ page import="com.javacources.service.UserService" %>
<%@ page import="com.javacources.entity.User" %>
<%@ page import="com.javacources.service.OrderService" %>
<%@ page import="com.javacources.entity.Order" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: tanya
  Date: 2/28/14
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>

<body>
<%
    UserService userService = (UserService) session.getAttribute("userService");
    if (userService == null){
        response.sendRedirect("/index.jsp");
    }

    User curUser = userService.getCurUser();
    if (curUser == null){
        response.sendRedirect("/index.jsp");
    }

    OrderService orderService = (OrderService) session.getAttribute("orderService");
    if (orderService == null){
        response.sendRedirect("/index.jsp");
    }
%>

<h1>Hello, <%=curUser.getLogin()%></h1>
<%
    List<Order> userOrders = orderService.findAllUserOrders(curUser);
    session.setAttribute("orderService", orderService);

%>


<%
    if ((userOrders != null) && (userOrders.size() > 0)){

%>
<h2>Please, get your orders:</h2>
<form name="orderForm" action="/order" method="Get">
<table>
    <tr>
        <th>Brand</th>
        <th>Model</th>
        <th>Year</th>
        <th>Delete your order, if you want</th>
    </tr>
    <%for (Order order: userOrders){%>

    <tr>
        <td><%=order.getCar().getBrand()%></td>
        <td><%=order.getCar().getModel()%></td>
        <td><%=order.getCar().getYearProduced()%></td>
        <td><input type="submit" value="Delete" name=<%=String.valueOf(userOrders.indexOf(order))%>
               />
        </td>
    </tr>
    <%}%>
</table>
</form>
<%}%>


<h2>You can submit the new order:</h2>
<form name="inputOrder" action="/order" method="POST">

    <table>
        <tr>
            <td>Brand:</td>
            <td>
                <!--<input type="text" name="brand"/>-->
                <select name="brand">
                    <option value="volvo" selected>Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="mercedes">Mercedes</option>
                    <option value="audi">Audi</option>
                    <option value="lada">Lada</option>
                    <option value="suzuki">Suzuki</option>
                </select>
            </td>
            <td>Model:</td>
            <td>
                <!--<input type="text" name="model"/>-->
                <select name="model">
                    <option value="Model1" selected>Model1</option>
                    <option value="Model2">Model2</option>
                    <option value="Model3">Model3</option>
                    <option value="Model4">Model4</option>
                    <option value="Model5">Model5</option>
                    <option value="Model6">Model6</option>
                </select>
            </td>
            <td>Year produced:</td>
            <td>
                <!--<input type="text" name="year"/>-->
                <select name="year">
                    <option value="2014" selected>2014</option>
                    <option value="2013">2013</option>
                    <option value="2012">2012</option>
                    <option value="2011">2011</option>
                    <option value="2010">2010</option>
                    <option value="2009">2009</option>
                </select>
            </td>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>

</form>

<h2>
    <form name="logOut" action="/logout" method="GET">
        <input type="submit" value="Log out"/></form>
</h2>
</body>
</html>

