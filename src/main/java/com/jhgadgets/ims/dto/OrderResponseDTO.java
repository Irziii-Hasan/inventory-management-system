package com.jhgadgets.ims.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jhgadgets.ims.model.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderResponseDTO {

	private Long id;
	private String customerName;
	private String phoneNumber;
	private String productName;
	private Integer quantity;
	private OrderStatus status;
	private BigDecimal sellingPrice;
	private LocalDate orderDate;
}

