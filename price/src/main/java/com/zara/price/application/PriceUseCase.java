package com.zara.price.application;

import com.zara.price.domain.dto.PriceDto;
import com.zara.price.domain.entities.Price;
import com.zara.price.domain.mappers.PriceMapper;
import com.zara.price.infra.inputport.PriceInputPort;
import com.zara.price.infra.outputport.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceUseCase implements PriceInputPort {


    private final EntityRepository entityRepository;
    private final PriceMapper priceMapper;
    /*
    public PriceUseCase(EntityRepository entityRepository, PriceMapper priceMapper) {
        this.entityRepository = entityRepository;
        this.priceMapper = priceMapper;
    }*/

    @Override
    public PriceDto getPriceFinal(PriceDto priceDto) {
        PriceDto priceDtoDB= PriceDto.builder().build();
        List<Price> priceList = entityRepository.getPriceByBrandIdProducIdDateApply(priceDto.getDateApply(), priceDto.getBrandId(), priceDto.getProductId());
        if (priceList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontro precio para los parametros ingresados");
        }
        if (priceList.size()==1){
            priceDtoDB = priceMapper.toDto(priceList.get(0));
        }else {
            Optional<Price> op = priceList.stream().max(Comparator.comparingInt(Price::getPriority));
            if (op.isPresent()){
                priceDtoDB = priceMapper.toDto( op.get());
            }
        }
        priceDtoDB.setDateApply(priceDto.getDateApply());
        return priceDtoDB;
    }
}
