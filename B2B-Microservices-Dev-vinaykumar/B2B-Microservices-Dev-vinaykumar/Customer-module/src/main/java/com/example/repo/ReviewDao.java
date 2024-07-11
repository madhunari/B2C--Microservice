package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Model.Product;
import com.example.Model.Review;
import com.example.Model.User;

@Repository
public interface ReviewDao extends JpaRepository<Review, Integer> {

	List<Review> findByProductIn(List<Product> products);

	@Query("SELECT r FROM Review r WHERE r.product.seller = :seller")
	List<Review> findAllOrdersBySeller(@Param("seller") User seller);

}
