package com.jhgadgets.ims.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


import lombok.Data;

@Data
public class OrderUpdateRequest {
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal sellingPrice;
    private LocalDate orderDate;
}
