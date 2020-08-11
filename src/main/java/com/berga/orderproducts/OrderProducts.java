package com.berga.orderproducts;

import java.math.BigDecimal;

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
	}
}
