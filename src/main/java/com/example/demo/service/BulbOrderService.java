package com.example.demo.service;

import com.example.demo.entity.BulbOrder;
import com.example.demo.entity.LightBulb;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BulbOrderRepository;
import com.example.demo.repository.LightBulbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BulbOrderService {

    private final BulbOrderRepository orderRepository;
    private final LightBulbRepository bulbRepository;

    public BulbOrder createOrder(Long bulbId, BulbOrder order) {
        // Lấy đối tượng bóng đèn không tìm thấy ném exception
        LightBulb bulb = bulbRepository.findById(bulbId)
                .orElseThrow(() -> new ResourceNotFoundException("Light bulb not found with id=" + bulbId));

        // Sử dụng builder để tạo đối tượng BulbOrder
        BulbOrder newOrder = BulbOrder.builder()
                .orderType(order.getOrderType())
                .quantity(order.getQuantity())
                .orderDate(LocalDateTime.now())
                .lightBulb(bulb)
                .build();

        log.info("Creating {} order for light bulb id={}", newOrder.getOrderType(), bulbId);
        return orderRepository.save(newOrder);
    }

    public List<BulbOrder> getOrders() {
        log.debug("Fetching all bulb orders");
        return orderRepository.findAll();
    }
}
