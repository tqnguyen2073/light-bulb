package com.example.demo.controller;

import com.example.demo.entity.LightBulb;
import com.example.demo.service.LightBulbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bulbs")
public class LightBulbController {

    private final LightBulbService bulbService;

    public LightBulbController(LightBulbService bulbService) {
        this.bulbService = bulbService;
    }

    @PostMapping
    public ResponseEntity<LightBulb> createBulb(@RequestBody LightBulb bulb) {
        LightBulb savedBulb = bulbService.createLightBulb(bulb);
        return new ResponseEntity<>(savedBulb, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LightBulb>> getAllBulbs() {
        return ResponseEntity.ok(bulbService.getAllLightBulbs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LightBulb> getBulbById(@PathVariable Long id) {
        return ResponseEntity.ok(bulbService.getLightBulbById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LightBulb> updateBulb(@PathVariable Long id, @RequestBody LightBulb bulbDetails) {
        return ResponseEntity.ok(bulbService.updateLightBulb(id, bulbDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBulb(@PathVariable Long id) {
        bulbService.deleteLightBulb(id);
        return ResponseEntity.noContent().build();
    }
}
