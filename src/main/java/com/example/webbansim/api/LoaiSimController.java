package com.example.webbansim.api;

import com.example.webbansim.model.dto.LoaiSim.LoaiSimDTO;
import com.example.webbansim.service.ILoaiSimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/admin/sim/type")
@RestController
public class LoaiSimController {
    @Autowired
    private ILoaiSimService iLoaiSimService;

    @GetMapping("/getName")
    public String getName(@RequestParam("idType") Integer id) {
        LoaiSimDTO loaiSimDTO = iLoaiSimService.findById(id);
        return loaiSimDTO.getTenType();
    }
}
