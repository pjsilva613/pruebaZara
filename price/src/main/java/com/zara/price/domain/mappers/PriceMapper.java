package com.zara.price.domain.mappers;

import com.zara.price.domain.dto.PriceDto;
import com.zara.price.domain.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "productId", source = "price.productId")
    @Mapping(target = "brandId", source = "price.brandId")
    @Mapping(target = "startDate", source = "price.startDate")
    @Mapping(target = "endDate", source = "price.endDate")
    @Mapping(target = "tariffApply", source = "price.priceList")
    @Mapping(target = "priceFinal", source = "price.price")
    PriceDto toDto(Price price);


    @Mapping(target = "productId", source = "priceDto.productId")
    @Mapping(target = "brandId", source = "priceDto.brandId")
    @Mapping(target = "startDate", source = "priceDto.startDate")
    @Mapping(target = "endDate", source = "priceDto.endDate")
    @Mapping(target = "priceList", source = "priceDto.tariffApply")
    @Mapping(target = "price", source = "priceDto.priceFinal")
    Price toEntity(PriceDto priceDto);

}
