package com.example.demo.service;

import com.example.demo.entity.LightBulb;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LightBulbRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LightBulbServiceTest {

    @Mock
    private LightBulbRepository lightBulbRepository;

    @InjectMocks
    private LightBulbService lightBulbService;

    private LightBulb bulb;

    @BeforeEach
    public void setup() {
        // Tạo đối tượng LightBulb mẫu
        bulb = new LightBulb();
        bulb.setId(1L);
        bulb.setModel("EcoLight 2000");
        bulb.setBrand("Osram");
        bulb.setWattage(40);
        bulb.setPrice(new BigDecimal("15.75"));
        bulb.setStock(50);
    }

    @Test
    public void testGetLightBulbById_Found() {
        // Tìm thấy LightBulb từ repository
        when(lightBulbRepository.findById(1L)).thenReturn(Optional.of(bulb));

        LightBulb found = lightBulbService.getLightBulbById(1L);
        Assertions.assertNotNull(found, "LightBulb không được null");
        Assertions.assertEquals("EcoLight 2000", found.getModel(), "Model không khớp");

        // Kiểm tra phương thức findById đã được gọi 1 lần
        verify(lightBulbRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetLightBulbById_NotFound() {
        // Giả lập không tìm thấy LightBulb
        when(lightBulbRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            lightBulbService.getLightBulbById(1L);
        });
    }

    @Test
    public void testCreateLightBulb() {
        // Giả lập lưu LightBulb mới vào repository
        when(lightBulbRepository.save(any(LightBulb.class))).thenReturn(bulb);

        LightBulb created = lightBulbService.createLightBulb(bulb);
        Assertions.assertNotNull(created, "LightBulb tạo ra không được null");
        Assertions.assertEquals("EcoLight 2000", created.getModel(), "Model không khớp");
        verify(lightBulbRepository, times(1)).save(bulb);
    }

    @Test
    public void testUpdateLightBulb() {
        // Giả lập tìm thấy LightBulb
        when(lightBulbRepository.findById(1L)).thenReturn(Optional.of(bulb));
        // Giả lập cập nhật thành công, repository trả về đối tượng cập nhật
        when(lightBulbRepository.save(any(LightBulb.class))).thenReturn(bulb);

        // Tạo đối tượng mới để cập nhật thông tin
        LightBulb updatedDetails = new LightBulb();
        updatedDetails.setBrand("Philips");
        updatedDetails.setModel("SuperLight 3000");
        updatedDetails.setWattage(60);
        updatedDetails.setPrice(new BigDecimal("20.00"));
        updatedDetails.setStock(30);

        LightBulb updatedBulb = lightBulbService.updateLightBulb(1L, updatedDetails);
        Assertions.assertEquals("Philips", updatedBulb.getBrand(), "Brand không khớp");
        Assertions.assertEquals("SuperLight 3000", updatedBulb.getModel(), "Model không khớp");

        verify(lightBulbRepository, times(1)).findById(1L);
        verify(lightBulbRepository, times(1)).save(bulb);
    }

    @Test
    public void testDeleteLightBulb() {
        // Tìm thấy LightBulb
        when(lightBulbRepository.findById(1L)).thenReturn(Optional.of(bulb));
        doNothing().when(lightBulbRepository).delete(bulb);

        // Xoá
        lightBulbService.deleteLightBulb(1L);
        verify(lightBulbRepository, times(1)).delete(bulb);
    }
}
