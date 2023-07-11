package com.example.webbansim.repository;

import com.example.webbansim.entity.NhaMang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhaMangRepository extends JpaRepository<NhaMang, Integer> {

    NhaMang findByIdNm(Integer id);

}
