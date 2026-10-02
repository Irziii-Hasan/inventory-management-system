package com.jhgadgets.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopUpdateRequest {
	private Long mallId;
	private String shopNumber;
	private String ownerName;
	private String contact;
}
