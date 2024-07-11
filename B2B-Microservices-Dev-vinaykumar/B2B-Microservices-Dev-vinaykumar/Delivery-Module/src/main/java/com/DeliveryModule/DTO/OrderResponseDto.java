package com.DeliveryModule.DTO;

import java.util.ArrayList;
import java.util.List;

import com.DeliveryModule.Model.Orders;

public class OrderResponseDto extends CommonApiResponse {

	private List<Orders> orders = new ArrayList<>();

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

}
