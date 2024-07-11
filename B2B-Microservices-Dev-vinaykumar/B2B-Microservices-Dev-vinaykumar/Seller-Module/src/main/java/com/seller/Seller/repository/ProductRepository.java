package com.seller.Seller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seller.Seller.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByStatusIn(List<String> status);

}
