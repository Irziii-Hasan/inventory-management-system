package com.jhgadgets.ims.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ShopProductResponseDTO {
	private Long id;
	private String productName;
	private String shopNumber;
	private String mallName;
	private BigDecimal price;
	private LocalDate visitDate;
	private String photoPath;
}
