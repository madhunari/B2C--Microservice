package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.Orders;
import com.example.repo.OrdersRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersRepository orderrepo;

	@Override
	public List<Orders> addOrder(List<Orders> orders) {
		return this.orderrepo.saveAll(orders);
	}
	
	@Override
	public List<Orders> getOrdersByUserAndStatusIn(com.example.Model.User user, List<String> status) {
		return this.orderrepo.findByUserAndStatusIn(user, status);
	}
}
