package com.example.webbansim.model.dto.TaiKhoan;

import com.example.webbansim.entity.TaiKhoan;
import com.example.webbansim.model.request.TaiKhoan.CreateTaiKhoanReq;
import org.mindrot.jbcrypt.BCrypt;

public class TaiKhoanMapper {
    public static TaiKhoanDTO toTaiKhoanDTO(TaiKhoan admin){
        return new TaiKhoanDTO()
                .setIdUser(admin.getId())
                .setUsername(admin.getUserName())
                .setPhone(admin.getPhone())
                .setPass(BCrypt.hashpw(admin.getPass(), BCrypt.gensalt(12)))
                .setEmail(admin.getEmail())
                .setIdRole(admin.getIdRole());
    }

    public static TaiKhoan toTaiKhoan(TaiKhoanDTO taiKhoanDTO){
        return new TaiKhoan()
                .setId(taiKhoanDTO.getIdUser())
                .setPass(taiKhoanDTO.getPass())
                .setPhone(taiKhoanDTO.getPhone())
                .setEmail(taiKhoanDTO.getEmail())
                .setUserName(taiKhoanDTO.getUsername())
                .setIdRole(taiKhoanDTO.getIdRole());
    }

    public static TaiKhoan toTaiKhoanAdmin (CreateTaiKhoanReq req){
        TaiKhoan admin = new TaiKhoan();
        admin.setUserName(req.getUsername());
        admin.setPhone(req.getPhone());
        admin.setEmail(req.getEmail());

        String hash = BCrypt.hashpw(req.getPass(), BCrypt.gensalt(12));
        admin.setPass(hash);
        return admin;
    }
}
