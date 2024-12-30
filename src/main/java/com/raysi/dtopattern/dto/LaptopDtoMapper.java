package com.raysi.dtopattern.dto;


import com.raysi.dtopattern.entity.Laptop;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LaptopDtoMapper implements Function<Laptop, LaptopDTO> {

    @Override
    public LaptopDTO apply(Laptop laptop) {
        return new LaptopDTO(
                laptop.getId(),
                laptop.getProcessor(),
                laptop.getBrand(),
                laptop.getModel(),
                laptop.getLunch(),
                laptop.getRam(),
                laptop.getSsd()
        );
    }
}
