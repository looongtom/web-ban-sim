package com.example.webbansim.model.dto.NhaMang;

import com.example.webbansim.entity.NhaMang;

public class NhaMangMapper {
    public static NhaMangDTO toNhaMangDTO(NhaMang nhaMang){
        return new NhaMangDTO()
                .setIdNm(nhaMang.getIdNm())
                .setTenNm(nhaMang.getTenNm());
    }
}
