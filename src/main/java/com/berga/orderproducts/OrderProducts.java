package com.berga.orderproducts;

import com.berga.orderproducts.repository.OrderProductsRepository;

public class OrderProducts {

	OrderProductsRepository repo = new OrderProductsRepository();
	public static void main(String[] args) {
		OrderProducts order = new OrderProducts();
		order.repo.findAllCategories();
	}
}
