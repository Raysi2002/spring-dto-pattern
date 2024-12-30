package com.raysi.dtopattern.service;

import com.raysi.dtopattern.dto.LaptopDTO;
import com.raysi.dtopattern.exception.ResourceNotFoundException;
import com.raysi.dtopattern.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImplementation implements LaptopService{

    private final LaptopRepository laptopRepository;
    @Autowired
    public LaptopServiceImplementation(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    @Override
    public List<LaptopDTO> fetchAllLaptop() {
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
        if(laptopDTOS.isEmpty()){
            throw new ResourceNotFoundException("701", "No Laptops Available");
        }
        try{
            return laptopDTOS;
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in Service Layer");
        }
    }

    @Override
    public Optional<LaptopDTO> fetchLaptop(Long id) {
        return Optional.empty();
    }
}
