package com.jhgadgets.ims.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "Customer name is required")
	private String customerName;
	
	@NotBlank (message = "Customer phone number is required")
	@Column(nullable = false, unique = true)
	@Pattern(regexp = "^03[0-9]{9}$", message = "Enter a valid Pakistani mobile number (e.g. 03001234567)")
	private String phoneNumber;
	
	private String address;

}
