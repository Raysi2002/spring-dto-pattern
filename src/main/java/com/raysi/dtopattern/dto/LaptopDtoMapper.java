package com.raysi.dtopattern.dto;

import com.raysi.dtopattern.entity.Laptop;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Function;

// Marks this class as a Spring service component
@Service
// Implements Function interface to map Laptop entity to LaptopDTO
public class LaptopDtoMapper implements Function<Laptop, LaptopDTO> {

    // Overrides the apply method to define the mapping logic
    @Override
    public LaptopDTO apply(Laptop laptop) {
        // Converts a Laptop entity into a LaptopDTO object
        return new LaptopDTO(
                // Maps each field from Laptop entity to LaptopDTO
                laptop.getId(),          // Maps the ID
                laptop.getProcessor(),   // Maps the processor details
                laptop.getBrand(),       // Maps the brand
                laptop.getModel(),       // Maps the model name/number
                laptop.getLunch(),       // Maps the launch date
                laptop.getRam(),         // Maps the RAM size
                laptop.getSsd()          // Maps the SSD size
        );
    }
}