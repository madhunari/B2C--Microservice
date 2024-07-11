package com.seller.Seller.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.seller.Seller.dto.CommonApiResponse;
import com.seller.Seller.exception.CategorySaveFailedException;
import com.seller.Seller.model.Category;
import com.seller.Seller.service.CategoryService;
import com.seller.Seller.utility.Constants.CategoryStatus;

import jakarta.transaction.Transactional;


@Component
@Transactional
public class CategoryResource {
	
	

	private final Logger LOG = LoggerFactory.getLogger(CategoryResource.class);

	@Autowired
	private CategoryService categoryService;


	public ResponseEntity<CommonApiResponse> addCategory(Category category) {
		
		LOG.info("Request received for add category");

		CommonApiResponse response = new CommonApiResponse();

		if (category == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		category.setStatus(CategoryStatus.ACTIVE.value());

		Category savedCategory = this.categoryService.addCategory(category);

		if (savedCategory == null) {
			throw new CategorySaveFailedException("Failed to add category");
		}

		response.setResponseMessage("Category Added Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

	}


}
