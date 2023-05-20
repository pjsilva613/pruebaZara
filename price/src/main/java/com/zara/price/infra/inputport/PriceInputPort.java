package com.zara.price.infra.inputport;

import com.zara.price.domain.dto.PriceDto;

public interface PriceInputPort {
    PriceDto getPriceFinal(PriceDto priceDto);
}
