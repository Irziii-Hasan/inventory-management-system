package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhgadgets.ims.dto.OrderResponseDTO;
import com.jhgadgets.ims.dto.OrderUpdateRequest;
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

	@Override
	public OrderResponseDTO updateOrder(Long orderId, OrderUpdateRequest request) {
	    Order existingOrder = orderRepository.findById(orderId)
	            .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

	    if (request.getCustomerId() != null) {
	        Customer customer = customerRepository.findById(request.getCustomerId())
	                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", request.getCustomerId()));
	        existingOrder.setCustomer(customer);
	    }

	    if (request.getQuantity() != null || request.getProductId() != null) {
	        if (existingOrder.getStatus() != OrderStatus.PENDING) {
	            throw new IllegalStateException("Cannot modify quantity or product of a confirmed/delivered order");
	        }
	        if (request.getProductId() != null) {
	            Product product = productRepository.findById(request.getProductId())
	                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", request.getProductId()));
	            existingOrder.setProduct(product);
	        }
	    }

	    orderMapper.updateOrderFromRequest(request, existingOrder);

	    Order updatedOrder = orderRepository.save(existingOrder);
	    logger.info("Order updated with id: {}", updatedOrder.getId());
	    return orderMapper.toDto(updatedOrder);
	}

	@Transactional
	@Override
	public OrderResponseDTO confirmOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));
		
		if(order.getStatus() != OrderStatus.PENDING){
			throw new IllegalStateException("Only PENDING orders can be confirmed");
		}
		
		boolean stockDeducted = inventoryService.updateInventoryAfterOrder(order);
		if(!stockDeducted) {
			throw new IllegalStateException("Not enough stock to confirm this order");
		}
		
		order.setStatus(OrderStatus.CONFIRMED);
		Order updatedOrder = orderRepository.save(order);
		logger.info("Order confirmed with id: {}",updatedOrder.getId());
		return orderMapper.toDto(updatedOrder);
		
	}

	@Transactional
	@Override
	public OrderResponseDTO deliverOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()-> new ResourceNotFoundException("Order", "id", orderId));
		if(order.getStatus()!= OrderStatus.CONFIRMED) {
			throw new IllegalStateException("Only confirmed status can delivered");
		}
		
		order.setStatus(OrderStatus.DELIVERED);
		Order updatedOrder =orderRepository.save(order);
		logger.info("Order delivered with id: {}",updatedOrder.getId());
		return orderMapper.toDto(updatedOrder);
	}

	@Transactional
	@Override
	public OrderResponseDTO cancelOrder(Long orderId) {
	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
	
	    if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
	        throw new IllegalStateException("Delivered or already cancelled orders cannot be cancelled");
	    }
	
	    if (order.getStatus() == OrderStatus.CONFIRMED) {
	        inventoryService.restoreInventoryAfterCancel(order.getProduct().getId(), order.getQuantity());
	    }
	
	    order.setStatus(OrderStatus.CANCELLED);
	    Order updatedOrder = orderRepository.save(order);
	    logger.info("Order cancelled with id: {}", updatedOrder.getId());
	    return orderMapper.toDto(updatedOrder);
	}

}
