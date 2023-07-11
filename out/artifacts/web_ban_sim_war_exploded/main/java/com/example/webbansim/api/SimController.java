package com.example.webbansim.api;

import com.example.webbansim.model.dto.Sim.SimDTO;
import com.example.webbansim.model.request.Sim.CreateSimReq;
import com.example.webbansim.model.request.Sim.FindSimReq;
import com.example.webbansim.service.ISimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/admin/sim")
@Controller
public class SimController {

    @Autowired
    private ISimService simService;


    @GetMapping("/getAllSim")
    public String getSim(Model model){
        List<SimDTO> simList = simService.getAllSim();
//        return ResponseEntity.ok(simList);
        model.addAttribute("listSim",simList);
        return "tutorials";
    }

    @PostMapping("/addSim")
    public ResponseEntity<?> addSim(@RequestBody CreateSimReq newSim){
        SimDTO newDTO = new SimDTO()
                .setSo(newSim.getSo())
                .setPrice(newSim.getPrice())
                .setIdType(newSim.getIdType());
        return ResponseEntity.status(HttpStatus.OK).body(simService.saveSim(newDTO));
    }
    @PostMapping("/findSim")
    public ResponseEntity<?> findSim(@RequestBody FindSimReq findSimReq){
        return ResponseEntity.status(HttpStatus.OK).body(simService.findByScope(findSimReq));
    }

    @DeleteMapping("{idSim}")
    public void deleteSim(@PathVariable("idSim") Long id){
        simService.deleteSimById(id);
    }

}
