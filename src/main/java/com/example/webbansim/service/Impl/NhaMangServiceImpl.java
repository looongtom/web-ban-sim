package com.example.webbansim.service.Impl;

import com.example.webbansim.entity.NhaMang;
import com.example.webbansim.model.dto.NhaMang.NhaMangDTO;
import com.example.webbansim.model.dto.NhaMang.NhaMangMapper;
import com.example.webbansim.repository.NhaMangRepository;
import com.example.webbansim.service.INhaMangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NhaMangServiceImpl implements INhaMangService {
     @Autowired
     private NhaMangRepository nhaMangRepository;
    @Override
    public NhaMangDTO findById(Integer id) {
        NhaMang nhaMang = nhaMangRepository.findByIdNm(id);
        NhaMangDTO nhaMangDTO = new NhaMangDTO();
        nhaMangDTO.setIdNm(nhaMang.getIdNm());
        nhaMangDTO.setTenNm(nhaMang.getTenNm());
        return nhaMangDTO;
    }

    @Override
    public String getNameNhaMang(Integer id) {
        NhaMang nhaMang = nhaMangRepository.findByIdNm(id);
        return nhaMang.getTenNm();
    }

    @Override
    public List<NhaMangDTO> getListNm() {
        List<NhaMang> nhaMangList = nhaMangRepository.findAll();
        List<NhaMangDTO> dtoList = new ArrayList<>();
        for(NhaMang tmp: nhaMangList){
            dtoList.add(NhaMangMapper.toNhaMangDTO(tmp));
        }
        return dtoList;
    }
}
