package com.jhgadgets.ims.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PurchaseResponseDTO {

	private Long id;
	private String productName;
	private String shopNumber;
	private String mallName;
	private Integer quantity;
	private BigDecimal purchasePrice;
	private LocalDate  purchaseDate;
}
