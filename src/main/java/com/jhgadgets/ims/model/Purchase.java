package com.jhgadgets.ims.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table (name = "purchases")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false, name = "product_id")
	private Product product;
	
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false, name = "shop_id")
	private Shop shop;
	
	@NotNull(message = "Quantity is required")
	@Min(1)
	@Column(nullable = false)
	private Integer quantity;
	
	@NotNull(message = "Price is required")
	@Min(0)
	@Column(nullable = false)
	private BigDecimal purchasePrice;
	
	@Column(columnDefinition = "DATE DEFAULT  (CURRENT_DATE)")
	private LocalDate purchaseDate;

}
