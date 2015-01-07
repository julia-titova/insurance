package com.titova.insurance.service.implementation;

import com.titova.HibernateFactory;
import com.titova.insurance.model.Stat;
import com.titova.insurance.service.StatService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StatServiceImplementation implements StatService{

    @Override
    public void createStat(Stat stat) {
        //SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Stat readTask(int statId) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Stat stat = (Stat) session.get(Stat.class, statId);
        session.close();

        return stat;
    }

    @Override
    public void updateStat(Stat stat) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteStat(Stat stat) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(stat);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllStats() {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List statsList = session.createCriteria(Stat.class).list();
        session.close();

        return statsList;
    }
    
}
