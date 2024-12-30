package com.raysi.dtopattern.service;

import com.raysi.dtopattern.dto.LaptopDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LaptopService {
    List<LaptopDTO> fetchAllLaptop();
    Optional<LaptopDTO> fetchLaptop(Long id);
}
