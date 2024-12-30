package com.raysi.dtopattern.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "laptop")
public class Laptop {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "laptop_seq"
    )
    @SequenceGenerator(
            name = "laptop_seq",
            sequenceName = "laptop_seqence",
            allocationSize = 116
    )
    private Long id;
    private String processor;
    private String brand;
    private String model;
    private LocalDateTime lunch;
    private Integer ram;
    private Integer ssd;
    private String os;
}
