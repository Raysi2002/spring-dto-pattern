package com.raysi.dtopattern.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for updating laptop details.
 * This class is used to transfer data between the client and server when updating laptop information.
 */
@AllArgsConstructor  // Lombok annotation to generate a constructor with all fields.
@NoArgsConstructor   // Lombok annotation to generate a no-argument constructor.
@Data                // Lombok annotation to generate getters, setters, toString(), equals(), and hashCode() methods.
public class LaptopUpdateDTO {

    /**
     * Processor of the laptop.
     * Represents the CPU model (e.g., Intel Core i7, Apple M1).
     */
    private String processor;

    /**
     * RAM size of the laptop in gigabytes (GB).
     * Represents the amount of memory available for running applications.
     */
    private Integer ram;

    /**
     * SSD storage size of the laptop in gigabytes (GB).
     * Represents the storage capacity of the laptop for files and applications.
     */
    private Integer ssd;

    /**
     * Operating System of the laptop.
     * Represents the OS installed on the laptop (e.g., Windows 11, macOS Ventura).
     */
    private String os;
}