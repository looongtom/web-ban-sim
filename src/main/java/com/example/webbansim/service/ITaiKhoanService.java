package com.example.webbansim.service;

import com.example.webbansim.entity.TaiKhoan;
import com.example.webbansim.model.dto.TaiKhoan.TaiKhoanDTO;
import com.example.webbansim.model.request.TaiKhoan.FindTaiKhoanReq;

import java.util.List;


public interface ITaiKhoanService {
    List<TaiKhoanDTO> getListTaiKhoan();

    TaiKhoanDTO findTaiKhoanByEmail(String email);

    TaiKhoanDTO signup(TaiKhoanDTO taiKhoanDTO);

    TaiKhoan update(Integer userID, TaiKhoanDTO taiKhoanDTO);

    List<TaiKhoanDTO>findUser(FindTaiKhoanReq findTaiKhoanReq);
}
