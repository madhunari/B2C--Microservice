package com.DeliveryModule.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.DeliveryModule.DTO.OrderResponseDto;
import com.DeliveryModule.DTO.UpdateDeliveryStatusRequest;
import com.DeliveryModule.Exception.OrderSaveFailedException;
import com.DeliveryModule.Model.Orders;
import com.DeliveryModule.Model.User;
import com.DeliveryModule.Service.OrderService;
import com.DeliveryModule.Service.UserService;
import com.DeliveryModule.Utility.Constants.DeliveryStatus;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
public class OrderResource {

	//private final Logger LOG = LoggerFactory.getLogger(CartResource.class);

	/*
	 * @Autowired private CartService cartService;
	 */

	@Autowired
	private UserService userService;

	/*
	 * @Autowired private ProductService productService;
	 */

	@Autowired
	private OrderService orderService;

	public ResponseEntity<OrderResponseDto> updateDeliveryStatus(UpdateDeliveryStatusRequest request) {

		log.info("Request received for assigning the delivery person for order");

		OrderResponseDto response = new OrderResponseDto();

		if (request == null || request.getOrderId() == null || request.getDeliveryStatus() == null
				|| request.getDeliveryTime() == null || request.getDeliveryId() == 0) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User deliveryPerson = this.userService.getUserById(request.getDeliveryId());

		List<Orders> orders = this.orderService.getOrdersByOrderIdAndStatusIn(request.getOrderId(),
				Arrays.asList(DeliveryStatus.PENDING.value(), DeliveryStatus.DELIVERED.value(),
						DeliveryStatus.ON_THE_WAY.value(), DeliveryStatus.PROCESSING.value()));

		if (CollectionUtils.isEmpty(orders)) {
			response.setResponseMessage("no orders by found");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		if (deliveryPerson == null) {
			response.setOrders(orders);
			response.setResponseMessage("Delivery Person not found");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		for (Orders order : orders) {
			order.setStatus(request.getDeliveryStatus());
			order.setDeliveryDate(request.getDeliveryDate());
			order.setDeliveryTime(request.getDeliveryTime());

			if (request.getDeliveryStatus().equals(DeliveryStatus.DELIVERED.value())) {
				order.setDeliveryStatus(DeliveryStatus.DELIVERED.value());
			}
		}

		List<Orders> updatedOrders = this.orderService.updateOrders(orders);

		if (updatedOrders == null) {
			throw new OrderSaveFailedException("Failed to update Order delivery status");
		}

		response.setOrders(updatedOrders);
		response.setResponseMessage("Orders fetched successful");
		response.setSuccess(true);

		return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
	}

	public ResponseEntity<OrderResponseDto> fetchDeliveryOrders(int deliveryPersonId) {

		log.info("Request received for fetching all delivery orders");

		OrderResponseDto response = new OrderResponseDto();

		if (deliveryPersonId == 0) {
			response.setResponseMessage("Delivery Person Id missing");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User delivery = this.userService.getUserById(deliveryPersonId);

		if (delivery == null) {
			response.setResponseMessage("Delivery Person not found, failed to delivery orders");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Orders> orders = new ArrayList<>();

		orders = this.orderService.getOrdersByDeliveryPersonAndStatusIn(delivery,
				Arrays.asList(DeliveryStatus.PENDING.value(), DeliveryStatus.DELIVERED.value(),
						DeliveryStatus.ON_THE_WAY.value(), DeliveryStatus.PROCESSING.value()));

		if (CollectionUtils.isEmpty(orders)) {
			response.setResponseMessage("No orders found");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		response.setOrders(orders);
		response.setResponseMessage("Orders fetched successful");
		response.setSuccess(true);

		return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
	}

}
