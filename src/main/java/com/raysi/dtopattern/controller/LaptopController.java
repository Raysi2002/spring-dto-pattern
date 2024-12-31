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

@RestController
public class LaptopController {

    private final LaptopService laptopService;
    @Autowired
    public LaptopController(LaptopService laptopService){
        this.laptopService = laptopService;
    }

    @GetMapping("laptop")
    public ResponseEntity<List<LaptopDTO>> fetchLaptops(){
        List<LaptopDTO> laptopDTOS = laptopService.fetchAllLaptop();
        try{
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(laptopDTOS);
        }catch (Exception e){
            throw new  ResourceNotFoundException("801", "Something went wrong in controller layer!");
        }
    }

    @GetMapping("laptop/{id}")
    public ResponseEntity<Optional<LaptopDTO>> fetchLaptopById(@PathVariable Long id){
        Optional<LaptopDTO> laptopDTO = laptopService.fetchLaptop(id);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Accept-Datetime")
                    .body(laptopDTO);
        }catch (Exception e){
            throw new ResourceNotFoundException("1003", "Something went wrong in Controller layer");
        }
    }

    @PostMapping("/laptop")
    public ResponseEntity<?> saveLaptops(@Valid @RequestBody List<Laptop> laptops){
        try {
            laptopService.saveLaptops(laptops);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Accept-Datetime")
                    .body(laptops);
        }catch (Exception e){
            System.out.println("Something went wrong in Controller layer: " + e.getMessage());
        }
        return new ResponseEntity<>(laptops, HttpStatus.CREATED);
    }
}
