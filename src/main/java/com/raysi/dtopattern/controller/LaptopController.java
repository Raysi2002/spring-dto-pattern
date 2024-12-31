package com.raysi.dtopattern.controller;

import com.raysi.dtopattern.dto.LaptopDTO;
import com.raysi.dtopattern.entity.Laptop;
import com.raysi.dtopattern.exception.ResourceNotFoundException;
import com.raysi.dtopattern.service.LaptopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Marks this class as a REST controller that handles HTTP requests
@RestController
public class LaptopController {

    // Injects the LaptopService to handle business logic
    private final LaptopService laptopService;

    // Constructor-based dependency injection for LaptopService
    @Autowired
    public LaptopController(LaptopService laptopService){
        this.laptopService = laptopService;
    }

    // Handles GET requests to fetch all laptops
    @GetMapping("laptop")
    public ResponseEntity<List<LaptopDTO>> fetchLaptops(){
        // Fetch all laptop details as DTOs
        List<LaptopDTO> laptopDTOS = laptopService.fetchAllLaptop();
        try {
            // Returns the list of laptops with HTTP status 302 (FOUND)
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(laptopDTOS);
        } catch (Exception e) {
            // Throws a custom exception if an error occurs
            throw new ResourceNotFoundException("801", "Something went wrong in controller layer!");
        }
    }

    // Handles GET requests to fetch a specific laptop by its ID
    @GetMapping("laptop/{id}")
    public ResponseEntity<Optional<LaptopDTO>> fetchLaptopById(@PathVariable Long id){
        // Fetch the laptop details as DTO for the given ID
        Optional<LaptopDTO> laptopDTO = laptopService.fetchLaptop(id);
        try {
            // Returns the laptop details with HTTP status 200 (OK) and a custom header
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Accept-Datetime")
                    .body(laptopDTO);
        } catch (Exception e) {
            // Throws a custom exception if an error occurs
            throw new ResourceNotFoundException("1003", "Something went wrong in Controller layer");
        }
    }

    // Handles POST requests to save a list of laptops
    @PostMapping("/laptop")
    public ResponseEntity<?> saveLaptops(@Valid @RequestBody List<Laptop> laptops){
        try {
            // Delegates the save operation to the service layer
            laptopService.saveLaptops(laptops);
            // Returns the saved laptops with HTTP status 201 (CREATED) and a custom header
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Accept-Datetime")
                    .body(laptops);
        } catch (Exception e) {
            // Logs an error message if an exception occurs
            System.out.println("Something went wrong in Controller layer: " + e.getMessage());
        }
        // Returns a response with the list of laptops and HTTP status 201 (CREATED)
        return new ResponseEntity<>(laptops, HttpStatus.CREATED);
    }
}