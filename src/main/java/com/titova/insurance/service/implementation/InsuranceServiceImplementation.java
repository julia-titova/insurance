package com.titova.insurance.service.implementation;

import com.titova.HibernateFactory;
import com.titova.insurance.model.Insurance;
import com.titova.insurance.service.InsuranceService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InsuranceServiceImplementation implements InsuranceService {

    @Override
    public void createInsurance(Insurance insurance) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(insurance);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Insurance readInsurance(int insuranceId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Insurance insurance;
        insurance = (Insurance) session.get(Insurance.class, insuranceId);
        session.close();

        return insurance;
    }

    @Override
    public void updateInsurance(Insurance insurance) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(insurance);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteInsurance(Insurance insurance) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(insurance);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllInsurances() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List insurancesList = session.createCriteria(Insurance.class).list();
        session.close();

        return insurancesList;
    }
    
    @Override
    public Insurance getInsuranceByName(String name) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List insurancesList = session.createCriteria(Insurance.class).list();
        session.close();

        Insurance resultInsurance = null;
        for (int i = 0; i < insurancesList.size(); i++) {
            Insurance insurance = (Insurance) insurancesList.get(i);
            if (insurance.getName().equals(name)) {
                resultInsurance = insurance;
            }
        }

        return resultInsurance;
    }
    
}
