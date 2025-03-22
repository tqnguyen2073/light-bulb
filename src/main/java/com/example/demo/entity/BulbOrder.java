package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bulb_orders")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulbOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private Integer quantity;
    private LocalDateTime orderDate;

    // Quan hệ N-1 với LightBulb
    @ManyToOne
    @JoinColumn(name = "light_bulb_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private LightBulb lightBulb;
}
