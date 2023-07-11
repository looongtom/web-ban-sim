package com.example.webbansim.model.request.TaiKhoan;

import lombok.*;

@Data
public class CreateTaiKhoanReq {
    private String email;
    private String pass;
    private String username;
    private String phone;
    private Integer idRole;
}
