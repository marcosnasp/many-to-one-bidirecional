package com.example.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    private static SessionFactory buildSessionFactory() {
        try {
            // Use hibernate.cfg.xml to get a SessionFactory
            Configuration conf = new AnnotationConfiguration();
            conf.addResource("hibernate.cfg.xml");
            //conf.addClass(Cart.class);
            //conf.addClass(Item.class);
            conf = conf.configure();
            return conf.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
        getSessionFactory().close();
    }
}