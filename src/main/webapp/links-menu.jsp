<%@page import="com.titova.insurance.model.Insurance"%>
<%@page import="com.titova.insurance.service.InsuranceService"%>
<%@page import="com.titova.insurance.model.Customer"%>
<%@page import="com.titova.insurance.service.CustomerService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.titova.insurance.model.Link"%>
<%@page import="java.util.List"%>
<%@page import="com.titova.insurance.service.LinkService"%>
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
        <title>Links menu Page</title>
    </head>
    <body>
        <div class="container">
            <h3>Система социального страхования. Меню назначений</h3>
            <br>
            <h4>
                <span class="glyphicon glyphicon-user"></span>
                <%
                    SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
                    User user = sessionBean.getCurrentUser();
                    Integer userId = 0;
                    String login = "";
                    if (user != null) {
                        userId = user.getId();
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
                    <th>Заказчик</th>
                    <th>Страховка</th>
                    <th>Цена</th>
                    <th>Тип</th>
                </tr>
                <%
                    CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
                    List<Customer> customersList = new ArrayList<>();
                    customersList = customerService.getAllCustomersByUserId(userId);
                    out.write("<br>");
                    out.write("<br>");

                    for (int i = 0; i < customersList.size(); i++) {
                        Customer customer = customersList.get(i);
                        if (customer != null) {
                            LinkService linkService = (LinkService) SpringFactory.getspringApplicationContext().getBean("linkService");
                            List<Link> linksList = new ArrayList<>();
                            linksList = linkService.getAllLinksByCustomerId(customer.getId());
                            //Sortlist by Software ID
                            for (int b = 0; b < linksList.size() - 1; b++) {
                                for (int a = 1; a < linksList.size() - b; a++) {
                                    Link tempLink = linksList.get(a);
                                    if (tempLink.getInsuranceId()< linksList.get(a - 1).getInsuranceId()) {
                                        linksList.set(a, linksList.get(a - 1));
                                        linksList.set(a - 1, tempLink);
                                    }
                                }
                            }

                            out.write("<tr>");
                            out.write("<td>" + "Заказчик в списке №: " + i + "</td>");
                            out.write("</tr>");

                            // Out a cell with current project id
                            Integer totalBalance = 0;
                            for (int j = 0; j < linksList.size(); j++) {
                                Link link = linksList.get(j);
                                if (link != null){
                                out.write("<tr>");

                                out.write("<td>" + link.getId() + "</td>");
                                Customer tempCustomer = customerService.readCustomer(link.getCustomerId());
                                out.write("<td>" + tempCustomer.getName() + "</td>");

                                InsuranceService insuranceService = (InsuranceService) SpringFactory.getspringApplicationContext().getBean("insuranceService");
                                Integer insuranceId = link.getInsuranceId();
                                Insurance tempInsurance = insuranceService.readInsurance(insuranceId);
                                out.write("<td>" + tempInsurance.getName() + "</td>");
                                out.write("<td>" + tempInsurance.getPrice()+ "</td>");
                                out.write("<td>" + tempInsurance.getType() + "</td>"); 

                                totalBalance = totalBalance + tempInsurance.getPrice();
                                }
                            }

                            out.write("<tr>");
                            out.write("<td>" + "Итоговый баланс: " + totalBalance + "</td>");
                            out.write("</tr>");

                        }
                        out.write("");
                    }
                %>

            </table>


            <table> 
                <tr>
                    <td>
                        <form action="links-operations.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-link"></span> Операции с назначениями
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
