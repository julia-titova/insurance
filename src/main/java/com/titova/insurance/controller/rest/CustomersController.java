package com.titova.insurance.controller.rest;

import com.titova.SessionBean;
import com.titova.SpringFactory;
import com.titova.insurance.model.Customer;
import com.titova.insurance.model.Stat;
import com.titova.insurance.model.User;
import com.titova.insurance.service.CustomerService;
import com.titova.insurance.service.StatService;
import com.titova.insurance.service.UserService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("customers")
public class CustomersController {

    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramName") String name) {
        java.net.URI location = null;

        try {
            SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
            User currentUser = sessionBean.getCurrentUser();

            Customer customer = new Customer();
            customer.setId(0);
            customer.setName(name);
            customer.setDate(new Date());
            customer.setUserId(currentUser.getId());

            CustomerService projectService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
            projectService.createCustomer(customer);            
                        
            Stat stat = new Stat();
            stat.setId(0);
            stat.setDescription("User " + currentUser.getName() + " create a new customer with id: " + customer.getId());
            stat.setDate(new Date());
            
            StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
            statService.createStat(stat);

            location = new java.net.URI("../customers-menu.jsp");

        } catch (URISyntaxException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }

}
