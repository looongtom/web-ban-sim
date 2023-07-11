package com.example.webbansim.service;

import com.example.webbansim.model.dto.NhaMang.NhaMangDTO;

import java.util.List;

public interface INhaMangService {
    NhaMangDTO findById(Integer id);
    String getNameNhaMang(Integer id);

    List<NhaMangDTO> getListNm();
}
