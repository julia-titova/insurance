<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.titova.insurance.service.UserService"%>
<%@page import="com.titova.SpringFactory"%>
<%@page import="com.titova.insurance.model.User"%>
<%@page import="com.titova.SessionBean"%>
<%@page import="com.titova.SessionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="resources/js/bootstrap.min.js"></script>
        <link href="resources/css/insurance.css" rel="stylesheet">

        <title>Users menu page</title>
    </head>
    <body>
        <div class="container">
            <h3>Система социального страхования. Меню пользователей</h3>
            <br>
            <h4>
                <span class="glyphicon glyphicon-user"></span>
                <%
                    SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
                    User user = sessionBean.getCurrentUser();
                    String login = "";
                    if (user != null) {
                        login = user.getName();

                        String admin = user.getAdmin();
                        login += " (" + admin + ")";
                    }

                %>
                <%=login%>

            </h4>
            <br>

            <table class="table table-striped">
                <tr>
                    <th>Номер</th>
                    <th>Имя</th>
                    <th>Логин</th>
                    <th>Дата</th>
                    <th>Администратор</th>
                </tr>
                <%
                    UserService userService = (UserService) SpringFactory.getspringApplicationContext().getBean("userService");
                    List<User> usersList = new ArrayList<>();
                    usersList = userService.getAllUsers();

                    for (int i = 0; i < usersList.size(); i++) {
                        user = usersList.get(i);
                        if (user != null) {
                            out.write("<tr>");
                            int userId = user.getId();
                            out.write("<td>" + userId + "</td>");

                            String userName = user.getName();
                            out.write("<td>" + userName + "</td>");

                            String userLogin = user.getLogin();
                            out.write("<td>" + userLogin + "</td>");

                            Date userDate = user.getRegistrationDate();
                            if (userDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                                String date = sdf.format(userDate);
                                out.write("<td>" + date + "</td>");
                            }

                            String userAdmin = user.getAdmin();
                            out.write("<td>" + userAdmin + "</td>");

                            out.write("</tr>");
                        }

                        out.write("");
                    }
                %>

            </table>
            <br>


            <table> 
                <tr>
                    <td>
                        <form action="user-operations.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-user"></span> Операции пользователей
                            </button>
                        </form>
                    </td>
                    <td>
                        &nbsp
                    </td>
                    <td>
                        <form action="main-menu.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-home"></span> В главное меню
                            </button>
                        </form>
                    </td>
                </tr>
            </table>

        </div>
    </body>
</html>
