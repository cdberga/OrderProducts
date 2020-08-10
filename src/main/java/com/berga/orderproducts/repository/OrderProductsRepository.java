package com.berga.orderproducts.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.berga.orderproducts.model.Category;
import com.berga.orderproducts.model.Product;
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
	
	public void findProductsByCategoryName(String name) {
		Session session = HibernateUtil.getSession();
		String hql = "from Product where Category.name = :category";
		Query query = session.createQuery(hql);
		query.setParameter("category", name);

		List<Product> products = query.getResultList();
		for (Product product : products) {
			System.out.println(product.getName());
		}
		HibernateUtil.closeSession(session);
	}
	
	public void findProductsByDescription(String desc) {
		Session session = HibernateUtil.getSession();
		String hql = "from Product where description like :description";
		Query query = session.createQuery(hql);
		query.setParameter("description", "%" + desc + "%");
		
		List<Product> products = query.getResultList();
		for (Product product : products) {
			System.out.println(product.getName());
		}
		HibernateUtil.closeSession(session);
	}
	
	public void updateProduct(Product p) {
		Session session = HibernateUtil.getSession();
		String hql = "update Product set price = :price where id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("price", p.getPrice());
		query.setParameter("id", p.getId());
		int rowsAffected = query.executeUpdate();
		if(rowsAffected > 0 ) {
			System.out.println(rowsAffected + " Products updated.");
		}
		HibernateUtil.closeSession(session);
	}
}
