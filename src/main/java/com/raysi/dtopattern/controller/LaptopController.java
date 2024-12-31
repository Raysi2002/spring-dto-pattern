package com.raysi.dtopattern.controller;

import com.raysi.dtopattern.dto.LaptopDTO;
import com.raysi.dtopattern.dto.LaptopUpdateDTO;
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
    public ResponseEntity<?> saveLaptops(@RequestBody List<Laptop> laptops){
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

    @DeleteMapping("/laptop/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            // Attempt to delete the laptop using the service method
            laptopService.deleteLaptop(id);

            // If deletion is successful, return a 410 GONE status with a success message
            return ResponseEntity
                    .status(HttpStatus.GONE)  // HTTP status code 410 indicates the resource is no longer available
                    .header("Accept-Datetime") // Optionally include a header to track the date/time of the deletion request
                    .body("Laptop successfully deleted with id: " + id); // Body contains a success message
        } catch (ResourceNotFoundException e) {
            // If the ResourceNotFoundException is thrown (e.g., laptop not found), rethrow the exception to be handled globally
            throw e;
        } catch (Exception e) {
            // Catch any other unexpected exceptions and throw a custom ResourceNotFoundException with error code 1103
            // This ensures a structured error message in case something goes wrong in the Controller layer
            throw new ResourceNotFoundException("1103", "Something went wrong in Controller layer");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLaptopById(@PathVariable Long id, @RequestBody LaptopUpdateDTO laptopUpdateDTO) {
        try {
            // Calling the service layer to update the laptop information by ID
            laptopService.updateLaptop(id, laptopUpdateDTO);

            // Returning a response indicating the laptop update was successful
            // Status code 201 (Created) is used here, but typically 200 (OK) might be more appropriate for update operations
            return ResponseEntity
                    .status(HttpStatus.CREATED)  // HTTP status indicating successful creation, consider using HttpStatus.OK for updates
                    .header("Accept-Datetime")  // You can add a custom header to indicate the datetime of the update, though it’s not set here
                    .body(laptopUpdateDTO);  // Returning the updated LaptopUpdateDTO in the response body

        } catch (ResourceNotFoundException e) {
            // If the laptop resource with the provided ID is not found, the exception is rethrown
            throw e;

        } catch (Exception e) {
            // Handling any other unexpected errors that may occur
            // A custom error message is passed along with a specific error code ("1303")
            throw new ResourceNotFoundException("1303", "Something went wrong in controller layer");
        }
    }
}