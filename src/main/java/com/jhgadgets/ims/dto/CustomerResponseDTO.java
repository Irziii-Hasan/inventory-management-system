package com.jhgadgets.ims.dto;

import lombok.Data;

@Data
public class CustomerResponseDTO {
	private Long id;
	private String customerName;
	private String phoneNumber;
	private String address;

}
