package com.example.webbansim.repository;

import com.example.webbansim.entity.Sim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SimRepository extends JpaRepository<Sim,Integer> {
//    List<Sim>findSim(String so, Double price, Integer type);
    List<Sim>findBySo(String so);

}
