package com.example.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.DTO.AddReviewRequest;
import com.example.DTO.CommonApiResponse;
import com.example.Exceptions.ReviewSaveFailedException;
import com.example.Model.Product;
import com.example.Model.Review;
import com.example.Model.User;
import com.example.service.ProductService;
import com.example.service.ReviewService;
import com.example.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReviewResource {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;

	public ResponseEntity<CommonApiResponse> addReview(AddReviewRequest request) {

		log.info("request received for adding product review");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getUserId() == 0 || request.getProductId() == 0 || request.getStar() == 0
				|| request.getReview() == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(request.getUserId());

		if (user == null) {
			response.setResponseMessage("user not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = this.productService.getProductById(request.getProductId());

		if (product == null) {
			response.setResponseMessage("product not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Review review = new Review();
		review.setProduct(product);
		review.setReview(request.getReview());
		review.setStar(request.getStar());
		review.setUser(user);

		Review addedReview = this.reviewService.addReview(review);

		if (addedReview == null) {
			throw new ReviewSaveFailedException("Failed to save the review");
		}

		response.setResponseMessage("product review added successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}
}
