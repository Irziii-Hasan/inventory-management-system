package com.jhgadgets.ims.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class InventoryResponseDTO {
	
	private Long id;
	private Integer quantity;
	private BigDecimal averageCost;
	private String photoPath;
	private String productName;

}
