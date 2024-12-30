package com.raysi.dtopattern.dto;


import java.time.LocalDateTime;

public record LaptopDTO(
        Long id,
        String processor,
        String brand,
        String model,
        LocalDateTime lunch,
        Integer ram,
        Integer ssd
) {
}
//private Long id;
//private String processor;
//private String brand;
//private String model;
//private LocalDateTime lunch;
//private Integer RAM;
//private Integer SSD;
//private String os;