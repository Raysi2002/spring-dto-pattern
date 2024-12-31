package com.raysi.dtopattern.dto;

import java.time.LocalDateTime;

// Defines a Data Transfer Object (DTO) for Laptop
// This is a record, which is a concise way to create immutable data objects in Java
public record LaptopDTO(
        // Unique identifier for the laptop
        Long id,

        // Processor details (e.g., Intel i5, AMD Ryzen 7)
        String processor,

        // Brand of the laptop (e.g., Dell, HP, Lenovo)
        String brand,

        // Model name or number of the laptop
        String model,

        // Launch date of the laptop
        LocalDateTime lunch,

        // RAM size in GB
        Integer ram,

        // SSD size in GB
        Integer ssd
) {
}