package com.example.webbansim.api;

import com.example.webbansim.model.dto.NhaMang.NhaMangDTO;
import com.example.webbansim.service.INhaMangService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin/nm")
@RestController
public class NhaMangController {

    @Autowired
    private INhaMangService nhaMangService;

    @GetMapping("/getName")
    public String getName(@RequestParam("idNm") Integer id) {
          NhaMangDTO nhaMangDTO = nhaMangService.findById(id);
          return nhaMangDTO.getTenNm();
    }

}
