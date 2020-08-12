package com.berga.orderproducts.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.berga.orderproducts.model.Category;
import com.berga.orderproducts.model.Product;
import com.berga.orderproducts.util.DaoUtil;
import com.berga.orderproducts.util.HibernateUtil;

public class OrderProductsRepository {

	/*
	 * Category
	 */
	public void findAllCategories() {
		List<Category> categories = DaoUtil.queryList("from Category");
		System.out.println("Categories");
		for(Category category : categories) {
			System.out.println("  " + category.getName());
		}
	}
	
	public void insertCategory(Integer id, String name) {
		DaoUtil.executeUpdate(new Category(name));
	}
	
	public void deleteCategory(Integer id) {
		String hql = "delete from Category where CATEGORY_ID = :id";
		int rowsAffected = DaoUtil.executeUpdate(hql, "id", id);
		if(rowsAffected > 0 ) {
			System.out.println("Deleted " + rowsAffected + " categories.");
		}
	}
	
	public Category findCategory(String name) {
		return (Category) DaoUtil.queryList("from Category where name = :name", "name", name).get(0);
	}
	
	/*
	 * Product
	 */
	
	public void insertProduct(Product p) {
		DaoUtil.executeUpdate(p);
	}
	
	public void findProductsByCategoryName(String name) {
		String hql = "from Product p join p.category c where c.name = :category";

		List<Object[]> items = DaoUtil.queryList(hql, "category", name);
		for(Object []row : items) {
			Product p = (Product) row[0];
			Category c = (Category) row[1];
			System.out.println("Product: " + p.getName() + " - Category: " + c.getName());
		}
	}
	
	public List<Product> findProductsByDescription(String desc) {
		String hql = "from Product where description like :description";
		
		return DaoUtil.queryList(hql, "description", "%" + desc + "%");
	}
	
	public void updateProduct(Product p) {
		String hql = "update Product set price = :price where id=:id";
		int rowsAffected = DaoUtil.executeUpdate(hql, "price", p.getPrice(), "id", p.getId());
		if(rowsAffected > 0 ) {
			System.out.println(rowsAffected + " Products updated.");
		}
	}
	
	public void findProductsAndCategories() {
		String hql = "from Product p inner join p.category";

		List<Object[]> listResult = DaoUtil.queryList(hql);
		
		for(Object []row : listResult) {
			Product p = (Product) row[0];
			Category c = (Category) row[1];
			System.out.println("Product: " + p.getName() + " - Category: " + c.getName());
		}
	}

}
