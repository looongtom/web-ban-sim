package com.example.webbansim.repository;

import com.example.webbansim.entity.Sim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimRepository extends JpaRepository<Sim,Integer> {
//    List<Sim>findSim(String so, Double price, Integer type);
    List<Sim>findBySoContainingOrIdNmOrPrice(String so, Double price, Integer type);

    Sim findByIdSim(Long id);

    Page<Sim> findBySoContaining(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderByIdSimAsc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderByPriceAsc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderByIdNmAsc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderByIdTypeAsc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderBySoAsc(Pageable pageable,String so);


    Page<Sim> findBySoContainingOrderByIdSimDesc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderByPriceDesc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderByIdNmDesc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderByIdTypeDesc(Pageable pageable,String so);

    Page<Sim> findBySoContainingOrderBySoDesc(Pageable pageable,String so);


}
