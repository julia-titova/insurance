<%@page import="com.titova.insurance.model.User"%>
<%@page import="com.titova.SpringFactory"%>
<%@page import="com.titova.SessionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <script src="resources/js/bootstrap.min.js"></script>
        <link href="resources/css/insurance.css" rel="stylesheet">

        <title>Insurance operations page</title>
    </head>
    <body>
        <div class="container">
            <h3>Система социального страхования</h3>
            <h3>Операции по страховкам</h3>
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
            <form class="form-signin" method="GET" action="/insurance/webresources/insurance/create">
                <input name = "paramName" type="text" id="name" class="form-control" placeholder="название">
                <input name = "paramPrice" type="text" id="licensePrice" class="form-control" placeholder="цена"> 
                <br>          

                <table>
                    <tr>
                        <td>

                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-heart"></span> Создать страховку
                            </button>
                            </form>
                        </td>
                        <td>
                            &nbsp
                        </td>

                        <td>
                            <form action="insurance-menu.jsp">
                                <button class="btn btn-info">
                                    <span class="glyphicon glyphicon-heart"></span> В меню страховок
                                </button>
                            </form>
                        </td>
                    </tr>
                </table>    

            </form>
        </div>
    </body>
</html>