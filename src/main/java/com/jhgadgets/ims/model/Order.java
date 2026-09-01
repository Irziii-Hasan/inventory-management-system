package com.jhgadgets.ims.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jhgadgets.ims.model.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@NotNull(message = "Quantity should be 1 or greater")
	@Min(1)
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status ;
	
	@NotNull(message = "Selling price is required")
	@Min(0)
	@Column(nullable = false)
	private BigDecimal sellingPrice;
	
	@Column(columnDefinition = "DATE DEFAULT  (CURRENT_DATE)")
	private LocalDate orderDate;
}
