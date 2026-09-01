package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jhgadgets.ims.dto.CustomerResponseDTO;
import com.jhgadgets.ims.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	public CustomerResponseDTO toDto(Customer customer);
	public List<CustomerResponseDTO> toDTOList(List<Customer> customers);
}
