package com.jhgadgets.ims.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhgadgets.ims.dto.OrderResponseDTO;
import com.jhgadgets.ims.model.Order;
import com.jhgadgets.ims.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping
	public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody Order order){
		return new ResponseEntity<>(orderService.createOrder(order),HttpStatus.CREATED);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId){
		return new ResponseEntity<OrderResponseDTO>(orderService.getOrderById(orderId),HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomerId(@PathVariable Long customerId){
		return new ResponseEntity<List<OrderResponseDTO>>(orderService.getOrdersByCustomerId(customerId), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
		return new ResponseEntity<List<OrderResponseDTO>>(orderService.getAllOrders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId){
		orderService.deleteOrderById(orderId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
}
