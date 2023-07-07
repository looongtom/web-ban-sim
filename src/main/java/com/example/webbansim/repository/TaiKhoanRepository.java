package com.example.webbansim.repository;

import com.example.webbansim.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Integer> {
    List<TaiKhoan> findByEmail(String email);
}
