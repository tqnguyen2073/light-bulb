package com.example.demo.entity;

import com.example.demo.entity.BulbOrder;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "light_bulbs")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LightBulb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private Integer wattage;
    private BigDecimal price;
    private Integer stock;

    // Quan hệ 1-N với BulbOrder
    @OneToMany(mappedBy = "lightBulb", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<BulbOrder> orders = new ArrayList<>();
}
