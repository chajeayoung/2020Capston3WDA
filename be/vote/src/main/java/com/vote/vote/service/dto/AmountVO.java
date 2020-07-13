package com.vote.vote.service.dto;

import lombok.Data;
@Data
public class AmountVO {
    private Integer total, tax_free, vat, point, discount;
}