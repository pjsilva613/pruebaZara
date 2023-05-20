package com.zara.price.infra.inputadapter.http;


import com.zara.price.domain.dto.PriceDto;
import com.zara.price.infra.inputport.PriceInputPort;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("prices")
public class PriceAPI {
private final Logger logger= LoggerFactory.getLogger(PriceAPI.class);
    private final PriceInputPort priceInputPort;

    public PriceAPI(PriceInputPort priceInputPort) {
        this.priceInputPort = priceInputPort;
    }

    @PostMapping
    public ResponseEntity<PriceDto> getPriceFinal(@Valid @RequestBody PriceDto priceDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(bindingResult));
        }
        return ResponseEntity.ok(priceInputPort.getPriceFinal(priceDto));
    }

    private String formatMessage(BindingResult bindingResult) {
            List<Map<String, String>> errors= bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> {Map<String, String> error = new HashMap<>();
                        logger.info(String.format("Imprime error:  campo: %s, valor: %s", fieldError.getField(), fieldError.getDefaultMessage()));
                        error.put(fieldError.getField(), fieldError.getDefaultMessage());
                        return error;})
                    .toList();
        return errors.stream().map(Objects::toString).collect(Collectors.joining());
    }
}
