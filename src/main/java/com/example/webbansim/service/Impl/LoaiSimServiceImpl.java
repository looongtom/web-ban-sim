package com.example.webbansim.service.Impl;

import com.example.webbansim.entity.LoaiSim;
import com.example.webbansim.model.dto.LoaiSim.LoaiSimDTO;
import com.example.webbansim.model.dto.LoaiSim.LoaiSimMapper;
import com.example.webbansim.repository.LoaiSimRepository;
import com.example.webbansim.service.ILoaiSimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    @Override
    public Page<LoaiSimDTO> findPaginated(int pageNo, int pageSize, String keyword) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        Page<LoaiSim>  loaiSimList=null;
        if(keyword==null || keyword.equals("null")) keyword="";
        loaiSimList=loaiSimRepository.findByTenTypeContainsIgnoreCaseOrderByIdType(pageable,keyword);

        Page<LoaiSimDTO> dtoPage=loaiSimList.map(new Function<LoaiSim, LoaiSimDTO>() {
            @Override
            public LoaiSimDTO apply(LoaiSim loaiSim) {
                return LoaiSimMapper.toLoaiSimDTO(loaiSim);
            }
        });
        return dtoPage;
    }

    @Override
    public void deletedTypeById(Integer id) {
        System.out.println(id);
        loaiSimRepository.deleteById(id);
    }


}
