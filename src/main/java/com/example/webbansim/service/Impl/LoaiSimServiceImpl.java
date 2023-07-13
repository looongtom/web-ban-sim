package com.example.webbansim.service.Impl;

import com.example.webbansim.entity.LoaiSim;
import com.example.webbansim.model.dto.LoaiSim.LoaiSimDTO;
import com.example.webbansim.model.dto.LoaiSim.LoaiSimMapper;
import com.example.webbansim.repository.LoaiSimRepository;
import com.example.webbansim.service.ILoaiSimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoaiSimServiceImpl implements ILoaiSimService {

    @Autowired
    private LoaiSimRepository loaiSimRepository;

    @Override
    public LoaiSimDTO findById(Integer id) {
        LoaiSim loaiSim = loaiSimRepository.findByIdType(id);
        LoaiSimDTO loaiSimDTO = new LoaiSimDTO();
        loaiSimDTO.setIdType(loaiSim.getIdType());
        loaiSimDTO.setTenType(loaiSim.getTenType());
        return loaiSimDTO;
    }

    @Override
    public List<LoaiSimDTO> getListTypeSim() {
        List<LoaiSim>loaiSimList = loaiSimRepository.findAll();
        List<LoaiSimDTO>loaiSimDTOList = new ArrayList<>();
        for(LoaiSim tmp: loaiSimList){
            loaiSimDTOList.add(LoaiSimMapper.toLoaiSimDTO(tmp));
        }
        return  loaiSimDTOList;
    }

    @Override
    public List<LoaiSimDTO> getAllLoai() {
        List<LoaiSim> loaiSimList = loaiSimRepository.findAll();
        List<LoaiSimDTO> loaiSimDTOList = new ArrayList<>();
        for(LoaiSim loaiSim :loaiSimList){
            loaiSimDTOList.add(LoaiSimMapper.toLoaiSimDTO(loaiSim));
        }
        return loaiSimDTOList;
    }

    @Override
    public List<LoaiSimDTO> findByTen(String tenLoai) {
        List<LoaiSim> loaiSimList = loaiSimRepository.findByTenTypeContainsIgnoreCase(tenLoai);
        List<LoaiSimDTO> loaiSimDTOList = new ArrayList<>();
        for(LoaiSim loaiSim : loaiSimList){
            loaiSimDTOList.add(LoaiSimMapper.toLoaiSimDTO(loaiSim));
        }
        return loaiSimDTOList;
    }

    @Override
    public LoaiSimDTO saveType(LoaiSimDTO loaiSimDTO) {
        loaiSimRepository.save(LoaiSimMapper.toLoaiSim(loaiSimDTO));
        return  loaiSimDTO;
    }
}
