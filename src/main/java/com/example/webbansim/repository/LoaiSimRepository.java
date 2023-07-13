package com.example.webbansim.repository;

import com.example.webbansim.entity.LoaiSim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoaiSimRepository extends JpaRepository<LoaiSim,Integer> {
    LoaiSim findByIdType(Integer id);

    List<LoaiSim> findByTenTypeContainsIgnoreCase(String tenType);
}
