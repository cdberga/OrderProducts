package com.berga.orderproducts.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.berga.orderproducts.model.Category;
import com.berga.orderproducts.util.HibernateUtil;

public class OrderProductsRepository {

	public void findAllCategories() {
		Session session = HibernateUtil.getSession();
		String hql = "from Category";
		Query query = session.createQuery(hql);
		List<Category> categories = query.getResultList();
		
		for(Category category : categories) {
			System.out.println(category.getName());
		}
		HibernateUtil.closeSession(session);
	}
	
	public void insertCategory(Integer id, String name) {
		Session session = HibernateUtil.getSession();
		session.save(new Category(name));
		HibernateUtil.closeSession(session);
	}
}
