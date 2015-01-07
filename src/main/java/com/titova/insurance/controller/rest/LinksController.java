package com.titova.insurance.controller.rest;

import com.titova.SessionBean;
import com.titova.SpringFactory;
import com.titova.insurance.model.Link;
import com.titova.insurance.model.Customer;
import com.titova.insurance.model.Stat;
import com.titova.insurance.model.Insurance;
import com.titova.insurance.model.User;
import com.titova.insurance.service.LinkService;
import com.titova.insurance.service.CustomerService;
import com.titova.insurance.service.StatService;
import com.titova.insurance.service.InsuranceService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("links")
public class LinksController {

    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramCustomerName") String projectName, @QueryParam("paramInsuranceName") String insuranceName) {
        java.net.URI location = null;

        try {
            SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
            User currentUser = sessionBean.getCurrentUser();
            Integer currentUserId = currentUser.getId();

            Link link = new Link();
            link.setId(0);

            CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
            Customer customer = customerService.getCustomerByName(projectName);
            if (customer != null){
                link.setCustomerId(customer.getId());
            }

            InsuranceService insuranceService = (InsuranceService) SpringFactory.getspringApplicationContext().getBean("insuranceService");
            Insurance insurance = insuranceService.getInsuranceByName(insuranceName);
            if (insurance != null){
                link.setSoftwareId(insurance.getId());
            }

            link.setUserId(currentUserId);

            LinkService linkService = (LinkService) SpringFactory.getspringApplicationContext().getBean("linkService");
            linkService.createLink(link);
            
            Stat stat = new Stat();
            stat.setId(0);
            stat.setDescription("User " + currentUser.getName() + " create a new link with id: " + link.getId());
            stat.setDate(new Date());
            
            StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
            statService.createStat(stat);

            location = new java.net.URI("../links-menu.jsp");

        } catch (URISyntaxException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }

}
