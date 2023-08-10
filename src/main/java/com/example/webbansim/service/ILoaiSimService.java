package com.example.webbansim.service;

import com.example.webbansim.model.dto.LoaiSim.LoaiSimDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ILoaiSimService {
    LoaiSimDTO findById(Integer id);

    List<LoaiSimDTO> getListTypeSim();

    List<LoaiSimDTO>getAllLoai();

    List<LoaiSimDTO> findByTen(String tenLoai);

    LoaiSimDTO saveType(LoaiSimDTO loaiSimDTO);

    Page<LoaiSimDTO> findPaginated(int pageNo,int pageSize,String keyword);

    void deletedTypeById(Integer id);
}
