package com.example.webbansim.service.Impl;


import com.example.webbansim.entity.AppUser;
import com.example.webbansim.model.dto.AppUser.AppUserDTO;
import com.example.webbansim.model.dto.AppUser.AppUserMapper;
import com.example.webbansim.registration.token.ConfirmationToken;
import com.example.webbansim.registration.token.ConfirmationTokenService;
import com.example.webbansim.repository.AppUserRepository;
import com.example.webbansim.service.IAppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements UserDetailsService, IAppUserService {

    private final static String USER_NOT_FOUND =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExits = appUserRepository.
                findByEmail(appUser.getEmail())
                .isPresent();

        if(userExits){
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword =
                bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        //send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //send email
        return token;
    }

    public int enableAppUser(String email){
        return appUserRepository.enableAppUser(email);
    }

    @Override
    public AppUserDTO getInfo(Long id) {
        Optional user = appUserRepository.findById(id);
        return AppUserMapper.appUserDTO((AppUser) user.get());
    }

    @Override
    public AppUserDTO saveUser(AppUserDTO userDTO) {
//        appUserRepository.save(AppUserMapper.toAppUser(userDTO));

        Optional user = appUserRepository.findById(userDTO.getIdAppUser());

        AppUser userModel = (AppUser) user.get();
        userModel.setEmail(userDTO.getEmail());
        userModel.setFirstName(userDTO.getFirstName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setPhoto(userDTO.getPhoto());
        appUserRepository.save(userModel);

        return userDTO;
    }

    @Override
    public AppUserDTO findUserByEmail(String email) {
        Optional appUser = appUserRepository.findByEmail(email);
        System.out.println(appUser.toString());
        return AppUserMapper.appUserDTO( (AppUser) appUser.get());
    }
}
