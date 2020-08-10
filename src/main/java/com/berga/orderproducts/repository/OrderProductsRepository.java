package com.berga.orderproducts.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.berga.orderproducts.model.Category;
import com.berga.orderproducts.util.HibernateUtil;

public class OrderProductsRepository {

	HibernateUtil util = new HibernateUtil();

	public void findAllCategories() {
		Session session = util.getNewSession();
		String hql = "from Category";
		Query query = session.createQuery(hql, Category.class);
		List<Category> categories = query.getResultList();
		
		for(Category category : categories) {
			System.out.println(category.getName());
		}
		util.closeSession();
	}
}
