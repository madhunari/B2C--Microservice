package com.example.service;

import java.util.List;

import com.example.Model.Cart;
import com.example.Model.User;

public interface CartService {

	List<Cart> findByUser(User user);

	void deleteCarts(List<Cart> cart);

	Cart updateCart(Cart cart);

	void deleteCart(Cart cart);

	Cart getCartById(int cartId);

}
