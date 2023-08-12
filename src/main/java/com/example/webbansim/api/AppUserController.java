package com.example.webbansim.api;

import com.example.webbansim.model.dto.AppUser.AppUserDTO;
import com.example.webbansim.service.IAppUserService;

import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequestMapping("/api/v1/admin/user")
@Controller
public class AppUserController {

    @Autowired
    private IAppUserService iAppUserService;

    @GetMapping("/getInfo/{user_id}")
    public String getInfo(@PathVariable("user_id") Long userID, Model model){
        AppUserDTO appUserDTO =iAppUserService.getInfo(userID);
        model.addAttribute("user",appUserDTO);

        return "TaiKhoan/user_info";
    }

    @GetMapping("/findUserByEmail/{email}")
    public String findUserByEmail(@PathVariable("email") String email,RedirectAttributes redirectAttributes){
        try {
            AppUserDTO appUserDTO=iAppUserService.findUserByEmail(email);
            System.out.println(appUserDTO.getIdAppUser());
            return "redirect:/api/v1/admin/user/getInfo/"+appUserDTO.getIdAppUser();
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",e);
        }
        return "Sim/sim_paging";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("idAppUser") Long id,
                           @RequestParam("email") String email,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("photo")MultipartFile photo,
                           RedirectAttributes redirectAttributes){
        AppUserDTO appUserDTO=iAppUserService.getInfo(id);
        appUserDTO.setIdAppUser(id);
        appUserDTO.setEmail(email);
        appUserDTO.setFirstName(firstName);
        appUserDTO.setLastName(lastName);
        try{


            System.out.println(photo.getOriginalFilename());
            if(photo.isEmpty()){
                redirectAttributes.addFlashAttribute("message","file was empty");
                return "redirect:/api/v1/admin/user/getInfo/"+appUserDTO.getIdAppUser();
            }
            Path path= Paths.get("uploads/");
            InputStream inputStream=photo.getInputStream();
            Files.copy(inputStream,path.resolve(photo.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            appUserDTO.setPhoto(photo.getOriginalFilename().toLowerCase());

            iAppUserService.saveUser(appUserDTO);

            redirectAttributes.addFlashAttribute("message","User "+appUserDTO.getFirstName()+" has been updated");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",e);
        }
        return "redirect:/api/v1/admin/user/getInfo/"+appUserDTO.getIdAppUser();
    }


    @GetMapping("edit/{id}")
    public String editUser(@PathVariable ("id") Long id, Model model, RedirectAttributes redirectAttributes){
        try{
            AppUserDTO appUserDTO=iAppUserService.getInfo(id);
            model.addAttribute("user",appUserDTO);
            model.addAttribute("pageTitle","Edit user ( ID: "+id+" )");


            return "TaiKhoan/user_form";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",e);
        }
        return "redirect:/api/v1/admin/user/getInfo/"+id;
    }
}
