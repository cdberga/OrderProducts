package com.berga.orderproducts.util;

import javax.persistence.Query;

import org.hibernate.Session;

public class DaoUtil {

	public static void executeUpdate(Object o) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
	}

	public static int executeUpdate(String hql, Object... objects) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int index = 0; index< objects.length; index++) {
			query.setParameter((String) objects[index], objects[index+1]);
			++index;
		}
		int retQuant = query.executeUpdate();
		session.getTransaction().commit();	
		HibernateUtil.closeSession(session);

		return retQuant;
	}
}
