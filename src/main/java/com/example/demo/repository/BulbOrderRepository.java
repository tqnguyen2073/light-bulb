package com.example.demo.repository;

import com.example.demo.entity.BulbOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulbOrderRepository extends JpaRepository<BulbOrder, Long> {
}
