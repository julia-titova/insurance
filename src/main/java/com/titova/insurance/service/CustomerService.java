package com.titova.insurance.service;

import com.titova.insurance.model.Customer;
import java.util.List;


public interface CustomerService {
    
    void createCustomer(Customer customer);
    Customer readCustomer(int projectId);
    void updateCustomer(Customer project);
    void deleteCustomer(Customer project);
    
    List getAllCustomers();
    List getAllCustomersByUserId(Integer userId);
    
    Customer getCustomerByName(String name);
    
}
