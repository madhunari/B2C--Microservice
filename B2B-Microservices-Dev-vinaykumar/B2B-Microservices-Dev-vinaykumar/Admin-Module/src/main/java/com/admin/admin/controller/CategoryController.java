package com.admin.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.admin.dto.CommonApiResponse;
import com.admin.admin.model.Category;
import com.admin.admin.resource.CategoryResource;

@RestController
@RequestMapping("api/category")
public class CategoryController {

	@Autowired
	private CategoryResource categoryResource;

	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody Category category) {
		return categoryResource.addCategory(category);
	}

	@PutMapping("/update")
	public ResponseEntity<CommonApiResponse> updateCategory(@RequestBody Category category) {
		return categoryResource.updateCategory(category);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<CommonApiResponse> deleteCategory(@RequestParam("categoryId") int categoryId) {
		return categoryResource.deleteCategory(categoryId);
	}

}
