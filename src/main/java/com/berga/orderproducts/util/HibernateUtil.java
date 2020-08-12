package com.berga.orderproducts.util;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.berga.orderproducts.model.Category;
import com.berga.orderproducts.model.Order;
import com.berga.orderproducts.model.Product;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		// loads configurations and mappings
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Category.class);
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(Order.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// builds a session factory from the service registry
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void closeSession(Session s) {
		s.close();
	}

	public static void executeUpdate(Object o) {
		Session session = getSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		closeSession(session);
	}

	public static int executeUpdate(String hql, Object... objects) {
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int index = 0; index< objects.length; index++) {
			query.setParameter((String) objects[index], objects[index+1]);
			++index;
		}
		int retQuant = query.executeUpdate();
		session.getTransaction().commit();	
		closeSession(session);

		return retQuant;
	}
}
