
<%@ page import="com.javacources.service.UserService" %>
<%@ page import="com.javacources.entity.User" %>
<html>
<body>
<h2>Log in, please:</h2>
<%
    UserService userSrv = (UserService) session.getAttribute("userSrv");
    if (userSrv != null){
        User user = userSrv.getCurUser();
        if (user == null) {
            session.invalidate();
        }
    }

%>
<form name="input1" action="/login" method="POST">
    <input type="text" name="login"/>
    <input type="password" name="password"/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
