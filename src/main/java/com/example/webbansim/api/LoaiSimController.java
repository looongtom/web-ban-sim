package com.example.webbansim.api;

import com.example.webbansim.entity.LoaiSim;
import com.example.webbansim.model.dto.LoaiSim.LoaiSimDTO;
import com.example.webbansim.service.ILoaiSimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/admin/sim/type")
@Controller
public class LoaiSimController {
    @Autowired
    private ILoaiSimService iLoaiSimService;

    @GetMapping("/getName")
    public String getName(@RequestParam("idType") Integer id) {
        LoaiSimDTO loaiSimDTO = iLoaiSimService.findById(id);
        return loaiSimDTO.getTenType();
    }

    @GetMapping("/getAllType")
    public String getLoai(Model model, @Param("keyword") String keyword){
        try{
            List<LoaiSimDTO> loaiSimDTOList = new ArrayList<>();
            if(keyword==null){
                List<LoaiSimDTO> serviceList= iLoaiSimService.getAllLoai();
                serviceList.forEach(loaiSimDTOList::add);
            }
            else{
                List<LoaiSimDTO> simDTOList = iLoaiSimService.findByTen(keyword);
                simDTOList.forEach(loaiSimDTOList::add);
                model.addAttribute("keyword",keyword);
            }
            model.addAttribute("listLoaiSim",loaiSimDTOList);
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }
        return "LoaiSim/type_management.html";
    }

    @GetMapping("/addNewType")
    public String addNewType(Model model){
        LoaiSimDTO loaiSimDTO = new LoaiSimDTO();
        model.addAttribute("typeSim",loaiSimDTO);
        model.addAttribute("pageTitle","Create new type");

        return "LoaiSim/type_form";
    }

    @PostMapping("/save")
    public String saveType(LoaiSimDTO loaiSimDTO, RedirectAttributes redirectAttributes){
        try{
            iLoaiSimService.saveType(loaiSimDTO);
            redirectAttributes.addFlashAttribute("message","The type has been saved successfully!");
        }catch (Exception e){
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/api/v1/admin/sim/type/page/1/size/5";
    }

    @GetMapping("page/{pageNo}/size/{pageSize}")
    public String findPaginated(Model model, @PathVariable(value = "pageNo") int pageNo,@PathVariable(value = "pageSize") int pageSize,@Param("keyword") String keyword){
        Page<LoaiSimDTO> page=iLoaiSimService.findPaginated(pageNo,pageSize,keyword);
        List<LoaiSimDTO> loaiSimList=page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("keyword",keyword);

        model.addAttribute("listLoaiSim",loaiSimList);

        return"LoaiSim/loaisim_paging";

    }

    @GetMapping("/edit/{idType}")
    public String editType(@PathVariable("idType") Integer id,Model model,RedirectAttributes redirectAttributes){
        try{
            LoaiSimDTO loaiSimDTO=iLoaiSimService.findById(id);
            model.addAttribute("typeSim",loaiSimDTO);
            model.addAttribute("pageTitle","Edit type sim (ID: "+id+")");

            return "LoaiSim/type_form";

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/api/v1/admin/sim/type/page/1/size/5";
    }

    @GetMapping("/delete/{idType}")
    public String deleteType(@PathVariable("idType") Integer id,
                             Model model,RedirectAttributes redirectAttributes){
        try{
            iLoaiSimService.deletedTypeById(id);
            redirectAttributes.addFlashAttribute("message",
                    "Type sim id = "+id+" has been deleted successfully");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/api/v1/admin/sim/type/page/1/size/5";
    }
}
