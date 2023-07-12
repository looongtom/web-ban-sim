package com.example.webbansim.service;

import com.example.webbansim.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAppUserService {
    UserDetails loadUserByUsername(String email);
    String signUpUser(AppUser appUser);
    int enableAppUser(String email);
}
