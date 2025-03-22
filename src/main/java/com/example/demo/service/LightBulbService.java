package com.example.demo.service;

import com.example.demo.entity.LightBulb;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LightBulbRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LightBulbService {

    private final LightBulbRepository bulbRepository;

    public LightBulb createLightBulb(LightBulb bulb) {
        log.info("Creating light bulb: {} - {}", bulb.getBrand(), bulb.getModel());
        return bulbRepository.save(bulb);
    }

    public List<LightBulb> getAllLightBulbs() {
        log.debug("Fetching all light bulbs");
        return bulbRepository.findAll();
    }

    public LightBulb getLightBulbById(Long id) {
        log.info("Fetching light bulb with id={}", id);
        return bulbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Light bulb not found with id=" + id));
    }

    public LightBulb updateLightBulb(Long id, LightBulb bulbDetails) {
        // Lấy đối tượng cũ, nếu không có thì ném exception
        LightBulb bulb = getLightBulbById(id);
        // Copy thuộc tính từ bulbDetails sang bulb, loại trừ id và orders
        BeanUtils.copyProperties(bulbDetails, bulb, "id", "orders");
        log.info("Updating light bulb id={}", id);
        return bulbRepository.save(bulb);
    }

    public void deleteLightBulb(Long id) {
        LightBulb bulb = getLightBulbById(id);
        log.warn("Deleting light bulb id={}", id);
        bulbRepository.delete(bulb);
    }
}
