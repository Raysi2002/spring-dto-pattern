package com.raysi.dtopattern.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LaptopUpdateDTO {
    private String processor;
    private Integer ram;
    private Integer ssd;
    private String os;
}
