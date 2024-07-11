package com.example.service;

import java.util.List;

import com.example.Model.Orders;
import com.example.Model.User;

public interface OrderService {

	List<Orders> addOrder(List<Orders> orders);
    List<Orders> getOrdersByUserAndStatusIn(User user, List<String> status);

}
