package com.example.webbansim.api;

import com.example.webbansim.model.dto.TaiKhoan.TaiKhoanDTO;
import com.example.webbansim.model.request.TaiKhoan.CreateTaiKhoanReq;
import com.example.webbansim.model.request.TaiKhoan.FindTaiKhoanReq;
import com.example.webbansim.model.request.TaiKhoan.UpdateTaiKhoanReq;
import com.example.webbansim.service.ITaiKhoanService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/admin")
@RestController
public class TaiKhoanController {
    private Integer idAdmin=1;
    private Integer idUser=2;
    @Autowired
    public ITaiKhoanService taiKhoanService;

    @GetMapping("/getListAdmin")
    public ResponseEntity<?> getListAdmin() {
        List<TaiKhoanDTO> result = taiKhoanService.getListTaiKhoan(idAdmin);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/getListUser")
    public ResponseEntity<?> getListUser() {
        List<TaiKhoanDTO> result = taiKhoanService.getListTaiKhoan(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody CreateTaiKhoanReq createTaiKhoanReq){
        TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO()
                .setUsername(createTaiKhoanReq.getUsername())
                .setPass(createTaiKhoanReq.getPass())
                .setEmail(createTaiKhoanReq.getEmail())
                .setPhone(createTaiKhoanReq.getPhone())
                .setIdRole(2);
        return ResponseEntity.status(HttpStatus.OK).body(taiKhoanService.signup(taiKhoanDTO));
    }

    @PostMapping("/updateUser/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable("user_id") Integer userID,@RequestBody UpdateTaiKhoanReq updateTaiKhoanReq){
        TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO()
                .setUsername(updateTaiKhoanReq.getUsername())
                .setEmail(updateTaiKhoanReq.getEmail())
                .setPhone(updateTaiKhoanReq.getPhone());
        return ResponseEntity.status(HttpStatus.OK).body(taiKhoanService.update(userID,taiKhoanDTO));
    }

    @PostMapping("/findUser")
    public ResponseEntity<?> findUser(@RequestBody FindTaiKhoanReq findTaiKhoanReq){
        return ResponseEntity.status(HttpStatus.OK).body(taiKhoanService.findUser(findTaiKhoanReq));
    }

}
