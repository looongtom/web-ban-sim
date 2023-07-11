package com.example.webbansim.repository;

import com.example.webbansim.entity.Sim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SimRepository extends JpaRepository<Sim,Integer> {
//    List<Sim>findSim(String so, Double price, Integer type);
    List<Sim>findBySoOrPriceOrIdType(String so, Double price, Integer type);

    Sim findByIdSim(Long id);
}
