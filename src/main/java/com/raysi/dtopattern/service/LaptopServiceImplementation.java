package com.raysi.dtopattern.service;

import com.raysi.dtopattern.dto.LaptopDTO;
import com.raysi.dtopattern.entity.Laptop;
import com.raysi.dtopattern.exception.InvalidDataException;
import com.raysi.dtopattern.exception.ResourceNotFoundException;
import com.raysi.dtopattern.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if(laptop.isEmpty()){
            throw new ResourceNotFoundException("1001", "No data found for id " + id);
        }
        try {
            return laptop;
        }catch (Exception e){
            throw new ResourceNotFoundException("1002", "Something went wrong in service layer");
        }
    }

    @Override
    public void saveLaptops(List<Laptop> laptops) {
        if (laptops.isEmpty()){
            throw new InvalidDataException("901", "Empty data can't be accepted");
        }
        for(Laptop laptop : laptops){
            if(
                    laptop.getBrand() == null || laptop.getProcessor() == null || laptop.getRam() == null
            ){
                throw new InvalidDataException("901", "Empty data can't be accepted");
            }
        }
        try {
            laptopRepository.saveAll(laptops);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in Service Layer");
        }
    }
}
