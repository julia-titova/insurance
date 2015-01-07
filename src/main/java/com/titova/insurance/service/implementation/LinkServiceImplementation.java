package com.titova.insurance.service.implementation;

import com.titova.HibernateFactory;
import com.titova.insurance.model.Link;
import com.titova.insurance.service.LinkService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LinkServiceImplementation implements LinkService{

    @Override
    public void createLink(Link link) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(link);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Link readLink(int linkId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Link link = (Link) session.get(Link.class, linkId);
        session.close();

        return link;
    }

    @Override
    public void updateLink(Link link) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(link);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteLink(Link link) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(link);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllLinks() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List linksList = session.createCriteria(Link.class).list();
        session.close();

        return linksList;
    }

    @Override
    public List getAllLinksByCustomerId(Integer customerId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List linksList = session.createCriteria(Link.class).list();
        session.close();
        
        List customerLinksList = new ArrayList();
        for (int i = 0; i < linksList.size(); i++){
            Link link = (Link) linksList.get(i);
            if (link != null){
                if (link.getCustomerId() == customerId){
                    customerLinksList.add(link);
                }    
            }
            
        }

        return customerLinksList;
    }
    
}

