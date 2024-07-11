package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.AddReviewRequest;
import com.example.DTO.CommonApiResponse;
import com.example.resources.ReviewResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/product/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

	@Autowired
	private ReviewResource reviewResource;

	@PostMapping("/add")
	@Operation(summary = "Api to add product review")
	public ResponseEntity<CommonApiResponse> addProductReview(@RequestBody AddReviewRequest review) {
		return this.reviewResource.addReview(review);
	}

	

}
