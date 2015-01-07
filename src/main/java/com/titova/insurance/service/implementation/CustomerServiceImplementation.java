package com.titova.insurance.service.implementation;

import com.titova.HibernateFactory;
import com.titova.insurance.model.Customer;
import com.titova.insurance.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CustomerServiceImplementation implements CustomerService{

    @Override
    public void createCustomer(Customer customer) {
    //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Customer readCustomer(int customerId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer;
        customer = (Customer) session.get(Customer.class, customerId);
        session.close();

        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllCustomers() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List customersList = session.createCriteria(Customer.class).list();
        session.close();

        return customersList;
    }
    
    @Override
    public List getAllCustomersByUserId(Integer userId){
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List customersList = session.createCriteria(Customer.class).list();
        session.close();
        
        List userCustomersList = new ArrayList();
        for (int i = 0; i < customersList.size(); i++){
            Customer customer = (Customer) customersList.get(i);
            if (customer != null){
                if (customer.getUserId() == userId){
                    userCustomersList.add(customer);
                }    
            }
            
        }

        return userCustomersList;
    }
    
    @Override
    public Customer getCustomerByName(String name) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List customersList = session.createCriteria(Customer.class).list();
        session.close();
        
        Customer resultCustomer = null;
        for (int i = 0; i < customersList.size(); i++){
            Customer customer = (Customer) customersList.get(i);
            if (customer.getName().equals(name)){
                resultCustomer = customer;
            }
        }

        return resultCustomer;
    }
    
}
