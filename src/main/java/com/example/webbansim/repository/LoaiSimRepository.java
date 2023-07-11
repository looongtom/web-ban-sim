package com.example.webbansim.repository;

import com.example.webbansim.entity.LoaiSim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoaiSimRepository extends JpaRepository<LoaiSim,Integer> {
    LoaiSim findByIdType(Integer id);
}
