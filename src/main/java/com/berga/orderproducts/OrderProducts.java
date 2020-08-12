package com.berga.orderproducts;

import java.math.BigDecimal;
import java.util.List;

import com.berga.orderproducts.model.Category;
import com.berga.orderproducts.model.Product;
import com.berga.orderproducts.repository.OrderProductsRepository;

public class OrderProducts {

	OrderProductsRepository repo = new OrderProductsRepository();
	public static void main(String[] args) {
		OrderProducts order = new OrderProducts();
		order.repo.insertCategory(1, "Vegetables");
		order.repo.insertCategory(2, "Meats");
		Category c = order.repo.findCategory("Vegetables");
		Product p = new Product("Banana", "Brazilian fruit", new BigDecimal(4), c);
		order.repo.insertProduct(p);
		order.repo.findAllCategories();
		order.repo.findProductsByCategoryName("Vegetables");
		int idCat = order.repo.findCategory("Meats").getId();
		order.repo.deleteCategory(idCat);
		order.repo.findAllCategories();
		
		System.out.println("Product \"Brazilian fruit\": ");
		List<Product> products = order.repo.findProductsByDescription("Brazilian fruit");
		for (Product product : products) {
			System.out.println(product);
		}

		products.get(0).setPrice(new BigDecimal(3));
		order.repo.updateProduct(products.get(0));
		List<Product> products1 = order.repo.findProductsByDescription("Brazilian fruit");
		System.out.println("Updated Product");
		for (Product product : products1) {
			System.out.println(product);
		}

		System.out.println("All Products and Categories: ");
		order.repo.findProductsAndCategories();
	}
}
