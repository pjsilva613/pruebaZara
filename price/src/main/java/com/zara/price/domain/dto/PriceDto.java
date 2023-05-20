package com.zara.price.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class PriceDto {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer brandId;

    @NotNull
    private LocalDateTime dateApply;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer tariffApply;
    private Double priceFinal;
}
