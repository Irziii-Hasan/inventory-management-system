package com.jhgadgets.ims.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopProductUpdateRequest {

	private Long productId;
	
	private Long shopId;
	
	private BigDecimal price;
	
	private String photoPath;
	
	private LocalDate visitDate;

}
