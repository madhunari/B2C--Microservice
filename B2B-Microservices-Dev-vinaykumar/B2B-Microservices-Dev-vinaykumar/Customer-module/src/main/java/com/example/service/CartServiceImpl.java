package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.Cart;
import com.example.Model.User;
import com.example.repo.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartrepo;

	@Override
	public void deleteCarts(List<Cart> carts) {
		this.cartrepo.deleteAll(carts);
	}

	@Override
	public Cart getCartById(int cartId) {

		Optional<Cart> optionalCart = this.cartrepo.findById(cartId);

		if (optionalCart.isPresent()) {
			return optionalCart.get();
		}

		else {
			return null;
		}
	}

	@Override
	public List<Cart> findByUser(User user) {
		// TODO Auto-generated method stub
		return cartrepo.findByUser(user);
	}

	@Override
	public Cart updateCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartrepo.save(cart);
	}

	@Override
	public void deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		cartrepo.delete(cart);
	}

}
