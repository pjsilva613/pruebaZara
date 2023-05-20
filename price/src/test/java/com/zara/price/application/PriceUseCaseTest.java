package com.zara.price.application;

import com.zara.price.domain.dto.PriceDto;
import com.zara.price.domain.entities.Price;
import com.zara.price.domain.mappers.PriceMapper;
import com.zara.price.infra.inputport.PriceInputPort;
import com.zara.price.infra.outputport.EntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceUseCaseTest {

    @MockBean
    private EntityRepository entityRepository;

    @MockBean
    private PriceMapper priceMapper;

    @Autowired
    private PriceUseCase priceUseCase;

    @BeforeEach
    void setUp() {
    }

    //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void getPriceFinalTest1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateApply = LocalDateTime.parse("2020-06-14 10:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31 23:59:59", formatter);
        PriceDto priceDto = PriceDto
                .builder()
                .dateApply(dateApply)
                .brandId(1)
                .productId(35455)
                .build();
        Price priceDB = Price
                .builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .price(35.5)
                .productId(35455)
                .build();
        PriceDto priceDtoDB = PriceDto
                .builder()
                .dateApply(dateApply)
                .startDate(startDate)
                .endDate(endDate)
                .tariffApply(1)
                .priceFinal(35.5)
                .brandId(1)
                .productId(35455)
                .build();
        List<Price> priceList = Collections.singletonList(priceDB);
        Mockito.when(entityRepository.getPriceByBrandIdProducIdDateApply(priceDto.getDateApply(), priceDto.getBrandId(), priceDto.getProductId())).thenReturn(priceList);
        Mockito.when(priceMapper.toDto(priceDB)).thenReturn(priceDtoDB);
        PriceDto priceDtoDBTest = priceUseCase.getPriceFinal(priceDto);
        Assertions.assertEquals(35.5, priceDtoDBTest.getPriceFinal());
        Assertions.assertEquals(1, priceDtoDBTest.getBrandId());
        Assertions.assertEquals(35455, priceDtoDBTest.getProductId());
        Assertions.assertEquals(dateApply, priceDtoDBTest.getDateApply());
        Assertions.assertEquals(startDate, priceDtoDBTest.getStartDate());
        Assertions.assertEquals(endDate, priceDtoDBTest.getEndDate());
        Assertions.assertEquals(1, priceDtoDBTest.getTariffApply());
    }
    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void getPriceFinalTest2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateApply = LocalDateTime.parse("2020-06-14 16:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14 15:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2020-06-14 18:30:00", formatter);
        PriceDto priceDto = PriceDto
                .builder()
                .dateApply(dateApply)
                .brandId(1)
                .productId(35455)
                .build();
        Price priceDB = Price
                .builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .price(25.45)
                .productId(35455)
                .build();
        PriceDto priceDtoDB = PriceDto
                .builder()
                .dateApply(dateApply)
                .startDate(startDate)
                .endDate(endDate)
                .tariffApply(2)
                .priceFinal(25.45)
                .brandId(1)
                .productId(35455)
                .build();
        List<Price> priceList = Collections.singletonList(priceDB);
        Mockito.when(entityRepository.getPriceByBrandIdProducIdDateApply(priceDto.getDateApply(), priceDto.getBrandId(), priceDto.getProductId())).thenReturn(priceList);
        Mockito.when(priceMapper.toDto(priceDB)).thenReturn(priceDtoDB);
        PriceDto priceDtoDBTest = priceUseCase.getPriceFinal(priceDto);
        Assertions.assertEquals(25.45, priceDtoDBTest.getPriceFinal());
        Assertions.assertEquals(1, priceDtoDBTest.getBrandId());
        Assertions.assertEquals(35455, priceDtoDBTest.getProductId());
        Assertions.assertEquals(dateApply, priceDtoDBTest.getDateApply());
        Assertions.assertEquals(startDate, priceDtoDBTest.getStartDate());
        Assertions.assertEquals(endDate, priceDtoDBTest.getEndDate());
        Assertions.assertEquals(2, priceDtoDBTest.getTariffApply());
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void getPriceFinalTest3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateApply = LocalDateTime.parse("2020-06-14 21:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31 23:59:59", formatter);
        PriceDto priceDto = PriceDto
                .builder()
                .dateApply(dateApply)
                .brandId(1)
                .productId(35455)
                .build();
        Price priceDB = Price
                .builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .price(35.5)
                .productId(35455)
                .build();
        PriceDto priceDtoDB = PriceDto
                .builder()
                .dateApply(dateApply)
                .startDate(startDate)
                .endDate(endDate)
                .tariffApply(1)
                .priceFinal(35.5)
                .brandId(1)
                .productId(35455)
                .build();
        List<Price> priceList = Collections.singletonList(priceDB);
        Mockito.when(entityRepository.getPriceByBrandIdProducIdDateApply(priceDto.getDateApply(), priceDto.getBrandId(), priceDto.getProductId())).thenReturn(priceList);
        Mockito.when(priceMapper.toDto(priceDB)).thenReturn(priceDtoDB);
        PriceDto priceDtoDBTest = priceUseCase.getPriceFinal(priceDto);
        Assertions.assertEquals(35.5, priceDtoDBTest.getPriceFinal());
        Assertions.assertEquals(1, priceDtoDBTest.getBrandId());
        Assertions.assertEquals(35455, priceDtoDBTest.getProductId());
        Assertions.assertEquals(dateApply, priceDtoDBTest.getDateApply());
        Assertions.assertEquals(startDate, priceDtoDBTest.getStartDate());
        Assertions.assertEquals(endDate, priceDtoDBTest.getEndDate());
        Assertions.assertEquals(1, priceDtoDBTest.getTariffApply());
    }
    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    void getPriceFinalTest4() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateApply = LocalDateTime.parse("2020-06-15 10:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-15 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse( "2020-06-15 11:00:00", formatter);
        PriceDto priceDto = PriceDto
                .builder()
                .dateApply(dateApply)
                .brandId(1)
                .productId(35455)
                .build();
        Price priceDB = Price
                .builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .price(30.5)
                .productId(35455)
                .build();
        PriceDto priceDtoDB = PriceDto
                .builder()
                .dateApply(dateApply)
                .startDate(startDate)
                .endDate(endDate)
                .tariffApply(3)
                .priceFinal(30.5)
                .brandId(1)
                .productId(35455)
                .build();
        List<Price> priceList = Collections.singletonList(priceDB);
        Mockito.when(entityRepository.getPriceByBrandIdProducIdDateApply(priceDto.getDateApply(), priceDto.getBrandId(), priceDto.getProductId())).thenReturn(priceList);
        Mockito.when(priceMapper.toDto(priceDB)).thenReturn(priceDtoDB);
        PriceDto priceDtoDBTest = priceUseCase.getPriceFinal(priceDto);
        Assertions.assertEquals(30.5, priceDtoDBTest.getPriceFinal());
        Assertions.assertEquals(1, priceDtoDBTest.getBrandId());
        Assertions.assertEquals(35455, priceDtoDBTest.getProductId());
        Assertions.assertEquals(dateApply, priceDtoDBTest.getDateApply());
        Assertions.assertEquals(startDate, priceDtoDBTest.getStartDate());
        Assertions.assertEquals(endDate, priceDtoDBTest.getEndDate());
        Assertions.assertEquals(3, priceDtoDBTest.getTariffApply());
    }
    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    void getPriceFinalTest5() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateApply = LocalDateTime.parse("2020-06-16 21:00:00", formatter);
        LocalDateTime startDate = LocalDateTime.parse("2020-06-15 16:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31 23:59:59", formatter);
        PriceDto priceDto = PriceDto
                .builder()
                .dateApply(dateApply)
                .brandId(1)
                .productId(35455)
                .build();
        Price priceDB = Price
                .builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .price(38.95)
                .productId(35455)
                .build();
        PriceDto priceDtoDB = PriceDto
                .builder()
                .dateApply(dateApply)
                .startDate(startDate)
                .endDate(endDate)
                .tariffApply(4)
                .priceFinal(38.95)
                .brandId(1)
                .productId(35455)
                .build();
        List<Price> priceList = Collections.singletonList(priceDB);
        Mockito.when(entityRepository.getPriceByBrandIdProducIdDateApply(priceDto.getDateApply(), priceDto.getBrandId(), priceDto.getProductId())).thenReturn(priceList);
        Mockito.when(priceMapper.toDto(priceDB)).thenReturn(priceDtoDB);
        PriceDto priceDtoDBTest = priceUseCase.getPriceFinal(priceDto);
        Assertions.assertEquals(38.95, priceDtoDBTest.getPriceFinal());
        Assertions.assertEquals(1, priceDtoDBTest.getBrandId());
        Assertions.assertEquals(35455, priceDtoDBTest.getProductId());
        Assertions.assertEquals(dateApply, priceDtoDBTest.getDateApply());
        Assertions.assertEquals(startDate, priceDtoDBTest.getStartDate());
        Assertions.assertEquals(endDate, priceDtoDBTest.getEndDate());
        Assertions.assertEquals(4, priceDtoDBTest.getTariffApply());
    }
}