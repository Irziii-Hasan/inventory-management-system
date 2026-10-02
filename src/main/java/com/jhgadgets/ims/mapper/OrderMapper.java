package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jhgadgets.ims.dto.OrderResponseDTO;
import com.jhgadgets.ims.dto.OrderUpdateRequest;
import com.jhgadgets.ims.model.Order;

@Mapper (componentModel = "spring")
public interface OrderMapper {

	@Mapping(source = "customer.customerName", target = "customerName")
	@Mapping(source = "customer.phoneNumber", target = "phoneNumber")
	@Mapping(source = "product.name", target = "productName")
	
	public OrderResponseDTO toDto(Order order);
	public List<OrderResponseDTO> toDtoList(List<Order> orders);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "customer", ignore = true)
	@Mapping(target = "product", ignore = true)
	@Mapping(target = "status", ignore = true)
	void updateOrderFromRequest(OrderUpdateRequest source, @MappingTarget Order order);
}
