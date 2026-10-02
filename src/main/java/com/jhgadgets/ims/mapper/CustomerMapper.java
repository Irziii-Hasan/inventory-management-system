package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jhgadgets.ims.dto.CustomerResponseDTO;
import com.jhgadgets.ims.dto.CustomerUpdateRequest;
import com.jhgadgets.ims.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	public CustomerResponseDTO toDto(Customer customer);
	public List<CustomerResponseDTO> toDTOList(List<Customer> customers);
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	void updateCustomerFromRequestMapping(CustomerUpdateRequest source, @MappingTarget Customer target);
}
