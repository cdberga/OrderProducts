package com.berga.orderproducts.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private Session session;
	
	private static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			// loads configurations and mappings
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = 
					new StandardServiceRegistryBuilder().applySettings(
						configuration.getProperties()).build();
			
			// builds a session factory from the service registry
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		return sessionFactory;
	}
	
	public Session getNewSession() {
		session = getSessionFactory().openSession();
		return session;
	}
	
	public void closeSession() {
		session.close();
	}
}
