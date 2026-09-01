package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jhgadgets.ims.dto.OrderResponseDTO;
import com.jhgadgets.ims.model.Order;

@Mapper (componentModel = "spring")
public interface OrderMapper {

	@Mapping(source = "customer.customerName", target = "customerName")
	@Mapping(source = "customer.phoneNumber", target = "phoneNumber")
	@Mapping(source = "product.name", target = "productName")
	
	public OrderResponseDTO toDto(Order order);
	public List<OrderResponseDTO> toDtoList(List<Order> orders);
}
