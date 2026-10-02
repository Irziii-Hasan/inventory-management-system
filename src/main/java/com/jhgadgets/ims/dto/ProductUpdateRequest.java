package com.jhgadgets.ims.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

	private String name;

	private Long categoryId;
	private String photoPath;
}
