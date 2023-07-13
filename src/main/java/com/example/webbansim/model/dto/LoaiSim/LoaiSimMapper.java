package com.example.webbansim.model.dto.LoaiSim;

import com.example.webbansim.entity.LoaiSim;

public class LoaiSimMapper {
    public static LoaiSimDTO toLoaiSimDTO(LoaiSim loaiSim){
        return new LoaiSimDTO()
                .setIdType(loaiSim.getIdType())
                .setTenType(loaiSim.getTenType());
    }

    public static LoaiSim toLoaiSim(LoaiSimDTO loaiSimDTO){
        return new LoaiSim()
                .setIdType(loaiSimDTO.getIdType())
                .setTenType(loaiSimDTO.getTenType());
    }
}
