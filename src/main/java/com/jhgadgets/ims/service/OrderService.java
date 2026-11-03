package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.OrderResponseDTO;
import com.jhgadgets.ims.dto.OrderUpdateRequest;
import com.jhgadgets.ims.model.Order;

public interface OrderService {

	OrderResponseDTO createOrder(Order order);
	OrderResponseDTO getOrderById(Long orderId);
	List<OrderResponseDTO> getAllOrders();
	List<OrderResponseDTO> getOrdersByCustomerId(Long customerId);
	void deleteOrderById(Long orderId);
	OrderResponseDTO updateOrder(Long orderId, OrderUpdateRequest request);
	OrderResponseDTO confirmOrder(Long orderId);
	OrderResponseDTO deliverOrder(Long orderId);
	OrderResponseDTO cancelOrder(Long orderId);
}
