<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.titova.insurance.model.Insurance"%>
<%@page import="java.util.List"%>
<%@page import="com.titova.insurance.service.InsuranceService"%>
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

        <title>Insurance menu page</title>
    </head>
    <body>
        <div class="container">
            <h3>Система социального страхования. Меню страховок</h3>
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
                    <th>Название</th>
                    <th>Цена</th>
                    <th>Дата</th>
                    <th>Тип</th>
                </tr>
                <%
                    InsuranceService insuranceService = (InsuranceService) SpringFactory.getspringApplicationContext().getBean("insuranceService");
                    List<Insurance> insurancesList = new ArrayList<>();
                    insurancesList = insuranceService.getAllInsurances();
                    List<Insurance> currentUserInsurancesList = new ArrayList<>();

                    for (int i = 0; i < insurancesList.size(); i++) {
                        Insurance insurance = insurancesList.get(i);
                        if (insurance.getUserId() == user.getId()) {
                            currentUserInsurancesList.add(insurance);
                        }
                    }

                    for (int i = 0; i < currentUserInsurancesList.size(); i++) {
                        Insurance insurance = currentUserInsurancesList.get(i);
                        if (insurance != null) {
                            out.write("<tr>");
                            out.write("<td>" + insurance.getId() + "</td>");
                            out.write("<td>" + insurance.getName() + "</td>");
                            out.write("<td>" + insurance.getPrice() + "</td>");                            
                            Date softwareDate = insurance.getDate();
                            if (softwareDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                                String date = sdf.format(softwareDate);
                                out.write("<td>" + date + "</td>");
                            }

                            out.write("<td>" + insurance.getType() + "</td>"); 
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
                        <form action="insurance-operations.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-heart"></span> Операции по страховкам
                            </button>
                        </form>
                    </td>
                    <td>
                        &nbsp
                    </td>
                    <td>
                        <form action="main-menu.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-home"></span> Вернуться в главное меню
                            </button>
                        </form>
                    </td>
                </tr>
            </table>

        </div>
    </body>
</html>