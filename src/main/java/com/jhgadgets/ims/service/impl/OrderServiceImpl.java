package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhgadgets.ims.dto.OrderResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.OrderMapper;
import com.jhgadgets.ims.model.Customer;
import com.jhgadgets.ims.model.Order;
import com.jhgadgets.ims.model.Product;
import com.jhgadgets.ims.model.enums.OrderStatus;
import com.jhgadgets.ims.repository.CustomerRepository;
import com.jhgadgets.ims.repository.OrderRepository;
import com.jhgadgets.ims.repository.ProductRepository;
import com.jhgadgets.ims.service.InventoryService;
import com.jhgadgets.ims.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final InventoryService inventoryService;
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	


	public OrderServiceImpl(OrderRepository orderRepository, OrderMapper mapper, CustomerRepository customerRepository,
			ProductRepository productRepository, InventoryService inventoryService) {
		super();
		this.orderRepository = orderRepository;
		this.orderMapper = mapper;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.inventoryService = inventoryService;
	}

	@Override
	@Transactional
	public OrderResponseDTO createOrder(Order order) {
		Customer customer = customerRepository.findById(order.getCustomer().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Customer", "customerId", order.getCustomer().getId()));
		Product product = productRepository.findById(order.getProduct().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Product", "productId",order.getProduct().getId()));
		order.setCustomer(customer);
		order.setProduct(product);
		boolean updateInventory = inventoryService.updateInventoryAfterOrder(order);
		if(updateInventory) {
			order.setStatus(OrderStatus.CONFIRMED);
		}else {
			order.setStatus(OrderStatus.PENDING);
		}
		
		Order savedOrder = orderRepository.save(order);
		logger.info("Order create with id: {}", savedOrder.getId());
		return orderMapper.toDto(savedOrder);
	}

	@Override
	public OrderResponseDTO getOrderById(Long orderId) {
		return orderMapper.toDto(orderRepository.findById(orderId)
				.orElseThrow(()-> new ResourceNotFoundException("Order", "orderId", orderId)));
	}

	@Override
	public List<OrderResponseDTO> getAllOrders() {
		return orderMapper.toDtoList(orderRepository.findAll());
	}

	@Override
	public List<OrderResponseDTO> getOrdersByCustomerId(Long customerId) {
		return orderMapper.toDtoList(orderRepository.findByCustomerId(customerId));
	}

	@Override
	public void deleteOrderById(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()-> new ResourceNotFoundException("Order", "orderId", orderId));
		orderRepository.deleteById(order.getId());	
	}

}
