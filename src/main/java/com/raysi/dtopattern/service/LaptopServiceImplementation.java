package com.raysi.dtopattern.service;

import com.raysi.dtopattern.dto.LaptopDTO;
import com.raysi.dtopattern.dto.LaptopUpdateDTO;
import com.raysi.dtopattern.entity.Laptop;
import com.raysi.dtopattern.exception.InvalidDataException;
import com.raysi.dtopattern.exception.ResourceNotFoundException;
import com.raysi.dtopattern.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Marks this class as a service component in Spring's context
@Service
// Implements the LaptopService interface to define the business logic
public class LaptopServiceImplementation implements LaptopService {

    // Dependency on the repository layer to interact with the database
    private final LaptopRepository laptopRepository;

    // Constructor-based dependency injection
    @Autowired
    public LaptopServiceImplementation(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Fetches all laptops and maps them to LaptopDTO objects
    @Override
    public List<LaptopDTO> fetchAllLaptop() {
        // Retrieve all laptops and convert them to DTOs using a stream
        List<LaptopDTO> laptopDTOS = laptopRepository.findAll()
                .stream()
                .map(laptop -> new LaptopDTO(
                        laptop.getId(),
                        laptop.getProcessor(),
                        laptop.getBrand(),
                        laptop.getModel(),
                        laptop.getLunch(),
                        laptop.getRam(),
                        laptop.getSsd()
                )).toList();

        // Throw an exception if no laptops are found
        if (laptopDTOS.isEmpty()) {
            throw new ResourceNotFoundException("701", "No Laptops Available");
        }

        try {
            // Return the list of LaptopDTOs
            return laptopDTOS;
        } catch (Exception e) {
            // Catch unexpected errors and wrap them in a runtime exception
            throw new RuntimeException("Something went wrong in Service Layer");
        }
    }

    // Fetches a single laptop by its ID and maps it to a LaptopDTO
    @Override
    public Optional<LaptopDTO> fetchLaptop(Long id) {
        // Find the laptop by ID and convert it to a DTO
        Optional<LaptopDTO> laptop = laptopRepository.findById(id)
                .map(laptop1 -> new LaptopDTO(
                        laptop1.getId(),
                        laptop1.getProcessor(),
                        laptop1.getBrand(),
                        laptop1.getModel(),
                        laptop1.getLunch(),
                        laptop1.getRam(),
                        laptop1.getSsd()
                ));

        // Throw an exception if the laptop is not found
        if (laptop.isEmpty()) {
            throw new ResourceNotFoundException("1001", "No data found for id " + id);
        }

        try {
            // Return the LaptopDTO
            return laptop;
        } catch (Exception e) {
            // Catch unexpected errors and throw a custom exception
            throw new ResourceNotFoundException("1002", "Something went wrong in service layer");
        }
    }

    // Saves a list of laptops to the database
    @Override
    public void saveLaptops(List<Laptop> laptops) {
        // Validate that the input list is not empty
        if (laptops.isEmpty()) {
            throw new InvalidDataException("901", "Empty data can't be accepted");
        }

        // Validate that each laptop has all required fields
        for (Laptop laptop : laptops) {
            if (laptop.getBrand() == null || laptop.getProcessor() == null || laptop.getRam() == null) {
                throw new InvalidDataException("901", "Empty data can't be accepted");
            }
        }

        try {
            // Save all laptops to the database
            laptopRepository.saveAll(laptops);
        } catch (Exception e) {
            // Catch unexpected errors and wrap them in a runtime exception
            throw new RuntimeException("Something went wrong in Service Layer");
        }
    }

    @Override
    public void deleteLaptop(Long id) {
        // -------Can be replaced in a single line----------
        // The original code manually checks if the laptop with the given ID exists by iterating through all laptops.
        // It sets a flag to true if the laptop is found, and throws a ResourceNotFoundException if not.
        // This logic can be replaced by the more efficient existsById method from the repository.

        // boolean flag = false;
        // for (Laptop laptop : laptopRepository.findAll()){
        //     if(Objects.equals(laptop.getId(), id)){
        //         flag = true;
        //         break;
        //     }
        // }
        // if(!flag){
        //     throw new ResourceNotFoundException("1101", "No Laptop found with id : " + id);
        // }

        // Check if the laptop with the given ID exists in the repository using existsById.
        // This method is more efficient than manually iterating through all laptops.
        if (!laptopRepository.existsById(id)) {
            // If the laptop does not exist, throw a ResourceNotFoundException with error code 1101.
            throw new ResourceNotFoundException("1101", "No Laptop found with id: " + id);
        }

        try {
            // Attempt to delete the laptop by its ID using the repository method deleteById.
            laptopRepository.deleteById(id);
        } catch (Exception e) {
            // If an error occurs during deletion (e.g., database issue), catch the exception.
            // Throw a ResourceNotFoundException with error code 1102 indicating failure during deletion.
            throw new ResourceNotFoundException("1102", "No Laptop found with id : " + id);
        }
    }

    @Override
    public void updateLaptop(Long id, LaptopUpdateDTO laptopUpdateDTO) {
        // Fetch the existing laptop from the repository using the provided ID.
        Laptop existingLaptop = laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("1201", "No laptop available with id: " + id));

        try {
            // Update the laptop's RAM if a new value is provided.
            // Check if the new value in LaptopUpdateDTO is not null before updating.
            if (laptopUpdateDTO.getRam() != null) {
                existingLaptop.setRam(laptopUpdateDTO.getRam());
            }

            // Update the laptop's SSD if a new value is provided.
            // Ensure the SSD value is not null before setting the new value.
            if (laptopUpdateDTO.getSsd() != null) {
                existingLaptop.setSsd(laptopUpdateDTO.getSsd());
            }

            // Update the laptop's OS if a new value is provided.
            // Make sure the OS value in LaptopUpdateDTO is not null.
            if (laptopUpdateDTO.getOs() != null) {
                existingLaptop.setOs(laptopUpdateDTO.getOs());
            }

            // Update the laptop's processor if a new value is provided.
            // Ensure that the processor value is not null before updating.
            if (laptopUpdateDTO.getProcessor() != null) {
                existingLaptop.setProcessor(laptopUpdateDTO.getProcessor());
            }

            // Save the updated laptop back to the repository.
            laptopRepository.save(existingLaptop);

        } catch (Exception e) {
            // If any error occurs during the update process, throw a runtime exception with a relevant message.
            throw new RuntimeException("Something went wrong in Service layer", e);
        }
    }
}