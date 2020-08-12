package com.berga.orderproducts.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.berga.orderproducts.model.Category;
import com.berga.orderproducts.model.Product;
import com.berga.orderproducts.util.HibernateUtil;

public class OrderProductsRepository {

	/*
	 * Category
	 */
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
		HibernateUtil.executeUpdate(new Category(name));
	}
	
	public void deleteCategory(Integer id) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hql = "delete from Category where CATEGORY_ID = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		int rowsAffected = query.executeUpdate();
		if(rowsAffected > 0 ) {
			System.out.println("Deleted " + rowsAffected + " rows.");
		}
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
	}
	
	public Category findCategory(String name) {
		Session session = HibernateUtil.getSession();
		String hql = "from Category where name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		Category category = (Category) query.getSingleResult();
		HibernateUtil.closeSession(session);
		return category;
	}
	
	/*
	 * Product
	 */
	
	public void insertProduct(Product p) {
		HibernateUtil.executeUpdate(p);
	}
	
	public void findProductsByCategoryName(String name) {
		Session session = HibernateUtil.getSession();
		String hql = "from Product p join p.category c where c.name = :category";
		Query query = session.createQuery(hql);
		query.setParameter("category", name);

		List<Object[]> items = query.getResultList();
		for(Object []row : items) {
			Product p = (Product) row[0];
			Category c = (Category) row[1];
			System.out.println("Product: " + p.getName() + " - Category: " + c.getName());
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
		String hql = "update Product set price = :price where id=:id";
		int rowsAffected = HibernateUtil.executeUpdate(hql, "price", p.getPrice(), "id", p.getId());
		if(rowsAffected > 0 ) {
			System.out.println(rowsAffected + " Products updated.");
		}
	}
	
	private void findProductsAndCategories() {
		Session session = HibernateUtil.getSession();
		String hql = "from Product p inner join p.category";
		Query query = session.createQuery(hql);

		List<Object[]> listResult = query.getResultList();
		
		for(Object []row : listResult) {
			Product p = (Product) row[0];
			Category c = (Category) row[1];
			System.out.println("Product: " + p.getName() + " - Category: " + c.getName());
		}
		HibernateUtil.closeSession(session);
	}

}
