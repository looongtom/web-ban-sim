package com.example.webbansim.service;

import com.example.webbansim.entity.AppUser;
import com.example.webbansim.model.dto.AppUser.AppUserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAppUserService {
    UserDetails loadUserByUsername(String email);
    String signUpUser(AppUser appUser);
    int enableAppUser(String email);

    AppUserDTO getInfo(Long id);

    AppUserDTO saveUser(AppUserDTO userDTO);

    AppUserDTO findUserByEmail(String email);
}
