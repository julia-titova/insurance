package com.titova.insurance.controller.rest;

import com.titova.SessionBean;
import com.titova.SpringFactory;
import com.titova.insurance.model.Insurance;
import com.titova.insurance.model.Stat;
import com.titova.insurance.model.User;
import com.titova.insurance.service.InsuranceService;
import com.titova.insurance.service.StatService;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("insurance")
public class InsuranceController {
    
    @GET
    @Path("create")
    @Produces("text/html")
    public Response create(@QueryParam("paramName") String name, @QueryParam("paramPrice") int price, @QueryParam("paramType") String type) {
        java.net.URI location = null;

        try {
            SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
            User currentUser = sessionBean.getCurrentUser();

            Insurance insurance = new Insurance();
            insurance.setId(0);
            insurance.setName(name);
            insurance.setPrice(price);
            insurance.setDate(new Date());
            insurance.setUserId(currentUser.getId());
            insurance.setType(type);

            InsuranceService insuranceService = (InsuranceService) SpringFactory.getspringApplicationContext().getBean("insuranceService");
            insuranceService.createInsurance(insurance);
                        
            Stat stat = new Stat();
            stat.setId(0);
            stat.setDescription("User " + currentUser.getName() + " create a new insurance with id: " + insurance.getId());
            stat.setDate(new Date());
            
            StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
            statService.createStat(stat);

            location = new java.net.URI("../insurance-menu.jsp");

        } catch (URISyntaxException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.temporaryRedirect(location).build();
    }
    
}
