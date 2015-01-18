<%@page import="com.titova.insurance.model.Insurance"%>
<%@page import="com.titova.insurance.service.InsuranceService"%>
<%@page import="com.titova.insurance.model.Customer"%>
<%@page import="com.titova.insurance.service.CustomerService"%>
<%@page import="com.titova.insurance.model.Link"%>
<%@page import="com.titova.insurance.model.User"%>
<%@page import="com.titova.SpringFactory"%>
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

        <title>Form</title>
    </head>
    <body>
<body>
        <div class="container">
            <h3>Система социального страхования.</h3>
            <h4>Форма страхового договора на услугу<h4>
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
                    <th>Параметр</th>
                    <th>Описание</th>
                </tr>
                <%
                    Link link = sessionBean.getLink();
                    
                    out.write("<tr>");
                    out.write("<td>" + "ФИО заказчика:" + "</td>");
                    //out.write("<td>" + link.getCustomerId() + "</td>");
                    
                    CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
                    Customer customer = customerService.readCustomer(link.getCustomerId());
                    out.write("<td>" + customer.getName() + "</td>");
                    out.write("</tr>");
                    
                    out.write("<tr>");
                    out.write("<td>" + "Дата регистрации заказчика:" + "</td>");
                    out.write("<td>" + customer.getDate() + "</td>");
                    out.write("</tr>");
                    
                    InsuranceService insuranceService = (InsuranceService) SpringFactory.getspringApplicationContext().getBean("insuranceService");
                    Insurance insurance = insuranceService.readInsurance(link.getInsuranceId());
                    
                    out.write("<tr>");
                    out.write("<td>" + "Название услуги:" + "</td>");
                    out.write("<td>" + insurance.getName() + "<td>");
                    out.write("</tr>");
                    
                    out.write("<tr>");
                    out.write("<td>" + "Тип услуги:" + "</td>");
                    out.write("<td>" + insurance.getType() + "</td>");
                    out.write("</tr>");
                    
                    out.write("<tr>");
                    out.write("<td>" + "Цена" + "</td>");
                    out.write("<td>" + insurance.getPrice()+ "</td>");
                    out.write("</tr>");
                    
                    out.write("<tr>");
                    out.write("<td>" + "Период" + "</td>");
                    out.write("<td>" + "12 месяцев" + "</td>");
                    out.write("</tr>");
                    
//                    List<Customer> customersList = new ArrayList<>();
//                    customersList = customerService.getAllCustomers();
//                    List<Customer> currentUserCustomersList = new ArrayList<>();
//                    
//                    for (int i = 0; i < customersList.size(); i++) {
//                        Customer customer = customersList.get(i);
//                        if (customer.getUserId() == user.getId()){
//                            currentUserCustomersList.add(customer);
//                        }
//                    }
//                    
//
//                    for (int i = 0; i < currentUserCustomersList.size(); i++) {
//                        Customer customer = currentUserCustomersList.get(i);
//                        if (customer != null) {
//                            out.write("<tr>");
//                            int customerId = customer.getId();
//                            out.write("<td>" + customerId + "</td>");
//
//                            String customerName = customer.getName();
//                            out.write("<td>" + customerName + "</td>");
//
//                            Date projectDate = customer.getDate();
//                            if (projectDate != null) {
//                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
//                                String date = sdf.format(projectDate);
//                                out.write("<td>" + date + "</td>");
//                            }
//
//                            out.write("</tr>");
//                        }
//
//                        out.write("");
//                    }
                %>

            </table>
            <br>

            <table> 
                <tr>
                    <td>
                         <form class="form-signin" method="GET" action="/insurance/webresources/links/create_accept">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-user"></span> Подписать
                            </button>
                        </form>
                    </td>
                    <td>
                        &nbsp
                    </td>
                    <td>
                        <form action="links-menu.jsp">
                            <button class="btn btn-info" type="submit">
                                <span class="glyphicon glyphicon-home"></span> Отказаться
                            </button>
                        </form>
                    </td>
                </tr>
            </table>
            
            </div>
    </body>
</html>
