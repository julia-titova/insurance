package com.titova.insurance.controller.rest;

import com.titova.SessionBean;
import com.titova.SpringFactory;
import com.titova.insurance.service.UserService;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("logout")
public class LogOutController {
    
    public LogOutController() {
    }
    
    @GET
    @Produces("text/html")
    public Response login(@QueryParam("paramEmail") String login) {
        java.net.URI location = null;
        
        UserService userService = (UserService) SpringFactory.getspringApplicationContext().getBean("userService");
        userService = null;
        
        SessionBean sessionBean = (SessionBean) SpringFactory.getspringApplicationContext().getBean("sessionBean");
        sessionBean = null;
        
        try {
            location = new java.net.URI("../login.html");
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(LogOutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.temporaryRedirect(location).build();
    }
    
}
