package com.jhgadgets.ims.dto;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest {

	private String customerName;
	
	@Pattern(regexp = "^03[0-9]{9}$", message = "Enter a valid Pakistani mobile number (e.g. 03001234567)")
	private String phoneNumber;
	
	private String address;
}
