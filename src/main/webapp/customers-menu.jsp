<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.titova.insurance.model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.titova.insurance.service.CustomerService"%>
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
        <title>Customers menu Page</title>
    </head>
    <body>
        <div class="container">
            <h3>Система социального страхования. Меню заказчиков</h3>
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
                    <th>Дата</th>
                </tr>
                <%
                    CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
                    List<Customer> customersList = new ArrayList<>();
                    customersList = customerService.getAllCustomers();
                    List<Customer> currentUserCustomersList = new ArrayList<>();
                    
                    for (int i = 0; i < customersList.size(); i++) {
                        Customer customer = customersList.get(i);
                        if (customer.getUserId() == user.getId()){
                            currentUserCustomersList.add(customer);
                        }
                    }
                    

                    for (int i = 0; i < currentUserCustomersList.size(); i++) {
                        Customer customer = currentUserCustomersList.get(i);
                        if (customer != null) {
                            out.write("<tr>");
                            int customerId = customer.getId();
                            out.write("<td>" + customerId + "</td>");

                            String customerName = customer.getName();
                            out.write("<td>" + customerName + "</td>");

                            Date projectDate = customer.getDate();
                            if (projectDate != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
                                String date = sdf.format(projectDate);
                                out.write("<td>" + date + "</td>");
                            }

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
                        <form action="customers-operations.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-user"></span> Операции с заказчиками
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
