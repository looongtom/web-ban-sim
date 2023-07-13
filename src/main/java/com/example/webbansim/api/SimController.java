package com.example.webbansim.api;

import com.example.webbansim.model.dto.LoaiSim.LoaiSimDTO;
import com.example.webbansim.model.dto.NhaMang.NhaMangDTO;
import com.example.webbansim.model.dto.Sim.SimDTO;
import com.example.webbansim.model.request.Sim.CreateSimReq;
import com.example.webbansim.model.request.Sim.FindSimReq;
import com.example.webbansim.service.ILoaiSimService;
import com.example.webbansim.service.INhaMangService;
import com.example.webbansim.service.ISimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/admin/sim")
@Controller
public class SimController {

    @Autowired
    private ISimService simService;

    @Autowired
    private INhaMangService iNhaMangService;

    @Autowired
    private ILoaiSimService iLoaiSimService;

    @GetMapping("/getAllSim")
    public String getSim(Model model, @Param("keyword") String keyword){
        try{
            List<SimDTO> dtoList = new ArrayList<>();
            if (keyword == null){
                List<SimDTO> serviceList = simService.getAllSim();
                for(SimDTO tmp:serviceList){
                    //set tên cho nhà mạng
                    String tenNhaMang = iNhaMangService.findById(tmp.getIdNm()).getTenNm();
                    tmp.setTenNm(tenNhaMang);
                    //set tên cho loại sim
                    String tenLoaiSim = iLoaiSimService.findById(tmp.getIdType()).getTenType();
                    tmp.setTenType(tenLoaiSim);

                }

                serviceList.forEach(dtoList::add);

            }else{
                FindSimReq findSimReq = new FindSimReq();
                findSimReq.setSo(keyword);
                List<SimDTO> serviceList = simService.findBySo(findSimReq);
                for(SimDTO tmp:serviceList){
                    //set tên cho nhà mạng
                    String tenNhaMang = iNhaMangService.findById(tmp.getIdNm()).getTenNm();
                    tmp.setTenNm(tenNhaMang);
                    //set tên cho loại sim
                    String tenLoaiSim = iLoaiSimService.findById(tmp.getIdType()).getTenType();
                    tmp.setTenType(tenLoaiSim);
                }
                serviceList.forEach(dtoList::add);

                model.addAttribute("keyword",keyword);
            }
            model.addAttribute("listSim",dtoList);
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }
        return "Sim/sim_management";
    }

    @PostMapping("/addSim")
    public ResponseEntity<?> addSim(@RequestBody CreateSimReq newSim){
        SimDTO newDTO = new SimDTO()
                .setSo(newSim.getSo())
                .setPrice(String.valueOf(newSim.getPrice()))
                .   setIdType(newSim.getIdType());
        return ResponseEntity.status(HttpStatus.OK).body(simService.saveSim(newDTO));
    }
    @PostMapping("/findSim")
    public ResponseEntity<?> findSim(@RequestBody FindSimReq findSimReq){
        return ResponseEntity.status(HttpStatus.OK).body(simService.findBySo(findSimReq));
    }

    @GetMapping("/delete/{idSim}")
    public String deleteSim(@PathVariable("idSim") Long id,
                          Model model, RedirectAttributes redirectAttributes){
        try{
            simService.deleteSimById(id);
            redirectAttributes.addFlashAttribute("message",
                    "Sim  id = "+id+" has been deleted successfully!");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/api/v1/admin/sim/getAllSim";
    }

    @GetMapping("/addNew")
    public String addNew(Model model){
        SimDTO simDTO = new SimDTO();
        model.addAttribute("sim",simDTO);
        model.addAttribute("pageTitle", "Create new Sim");

        List<NhaMangDTO> dtoList = iNhaMangService.getListNm();
        model.addAttribute("listNm",dtoList);

        List<LoaiSimDTO> loaiSimDTOList = iLoaiSimService.getListTypeSim();
        model.addAttribute("listTypeSim",loaiSimDTOList);

        return "Sim/sim_form";
    }

    @PostMapping("/save")
    public String saveSim(SimDTO simDTO , RedirectAttributes redirectAttributes){
        try {
            simService.saveSim(simDTO);
            redirectAttributes.addFlashAttribute("message","The sim has been saved successfully!");
        }catch (Exception e){
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/api/v1/admin/sim/getAllSim";
    }

    @GetMapping("/edit/{idSim}")
    public String editTutorial(@PathVariable("idSim") Long id, Model model, RedirectAttributes redirectAttributes) {
    try{
        SimDTO simDTO = simService.findByIdSim(id);
        model.addAttribute("sim",simDTO);
        model.addAttribute("pageTitle", "Edit Tutorial (ID: " + id + ")");

        List<NhaMangDTO> nhaMangtoList = iNhaMangService.getListNm();
        model.addAttribute("listNm",nhaMangtoList);

        List<LoaiSimDTO> loaiSimDTOList = iLoaiSimService.getListTypeSim();
        model.addAttribute("listTypeSim",loaiSimDTOList);

        return "Sim/sim_form";

    }catch (Exception e){
        redirectAttributes.addFlashAttribute("message", e.getMessage());
    }
        return "redirect:/api/v1/admin/sim/getAllSim";
    }

    @GetMapping("/searchSim")
    public String searchSim(Model model, @Param("keyword") String keyword){
        try{
            List<SimDTO> dtoList = new ArrayList<>();
            if (keyword == null){
                simService.getAllSim().forEach(dtoList::add);
            }else{
                FindSimReq findSimReq = new FindSimReq();
                findSimReq.setSo(keyword);
                simService.findBySo(findSimReq).forEach(dtoList::add);
                model.addAttribute("keyword",keyword);
            }
            model.addAttribute("listSim",dtoList);
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }
        return "Sim/sim_management";
    }

}
