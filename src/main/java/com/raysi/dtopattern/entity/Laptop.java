package com.raysi.dtopattern.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "laptop")
@ToString
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
    @CurrentTimestamp
    private LocalDateTime lunch;
    private Integer ram;
    private Integer ssd;
    private String os;
}
