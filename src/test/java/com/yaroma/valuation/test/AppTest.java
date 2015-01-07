package com.yaroma.valuation.test;

import com.titova.SpringFactory;
import com.titova.insurance.model.Customer;
import com.titova.insurance.model.Link;
import com.titova.insurance.model.Insurance;
import com.titova.insurance.model.Stat;
import com.titova.insurance.model.User;
import com.titova.insurance.service.CustomerService;
import com.titova.insurance.service.LinkService;
import com.titova.insurance.service.InsuranceService;
import com.titova.insurance.service.StatService;
import com.titova.insurance.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AppTest {

    @Test
    public void testUsers() {
        System.out.println("Working Directory = "
                + System.getProperty("user.dir"));

        // User service test!
        System.out.println("User service test.");

        UserService userService = (UserService) SpringFactory.getspringApplicationContext().getBean("userService");

        User user = new User();
        user.setId(0);
        user.setLogin("hibernateUser");
        String userName = "Hibernate test user";
        user.setName(userName);
        user.setPassword("hibernate");
        user.setRegistrationDate(new Date());

        userService.createUser(user);
        int userId = user.getId();
        System.out.println("create user done. User id: " + userId);

        user = userService.readUser(userId);
        if (!user.getName().equals(userName)) {
            fail("User name from create and read operations are not equals!!!");
        } else {
            System.out.println("Read user done!");
        }

        userName = "new Name";
        user.setName(userName);
        userService.updateUser(user);
        userId = user.getId();
        user = userService.readUser(userId);
        if (!user.getName().equals(userName)) {
            fail("User name from update and read operations are not equals!!!");
        } else {
            System.out.println("Update user done!");
        }

        String userLogin = user.getLogin();
        User tempUser = userService.getUserByLogin(userLogin);
        System.out.println("Test userService.getUserByLogin: " + tempUser.getName());

        userService.deleteUser(user);
        System.out.println("Delete user done!");

        // Customer service test. -----------------------------------------------       
        System.out.println("Customer service test.");
        CustomerService customerService = (CustomerService) SpringFactory.getspringApplicationContext().getBean("customerService");
        Customer customer = new Customer();
        customer.setId(0);
        customer.setName("Customer test");
        customer.setDate(new Date());

        customerService.createCustomer(customer);
        System.out.println("create customer done. Project id: " + customer.getId());

        customerService.deleteCustomer(customer);
        System.out.println("Delete customer done!");
        
        
        
        // Insurance service test. -----------------------------------------------       
        System.out.println("Project service test.");
        InsuranceService insuranceService = (InsuranceService) SpringFactory.getspringApplicationContext().getBean("insuranceService");
        Insurance insurance = new Insurance();
        insurance.setId(0);
        insurance.setName("Insurance test");
        insurance.setPrice(100);
        insurance.setDate(new Date());
        insurance.setUserId(1);

        insuranceService.createInsurance(insurance);
        System.out.println("create insurance done. Insurance id: " + customer.getId());

        insuranceService.deleteInsurance(insurance);
        System.out.println("Delete insurance done!");

        // Stat service test. --------------------------------------------------      
        System.out.println("Link service test.");
        StatService statService = (StatService) SpringFactory.getspringApplicationContext().getBean("statService");
        Stat stat = new Stat();
        stat.setId(0);
        stat.setDate(new Date());
        stat.setDescription("First stat record!");

        statService.createStat(stat);
        System.out.println("Create stat done. Stat id: " + stat.getId());

        statService.deleteStat(stat);
        System.out.println("Delete stat done!");
        
        // Link service test. --------------------------------------------------
        
        LinkService linkService = (LinkService) SpringFactory.getspringApplicationContext().getBean("linkService");
        //linkService.createLink(link);
        
        
        List linksList = new ArrayList();
                linksList = linkService.getAllLinksByCustomerId(1);
                linksList  =null;
        
    }
}
