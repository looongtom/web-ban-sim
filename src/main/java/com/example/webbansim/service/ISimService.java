package com.example.webbansim.service;

import com.example.webbansim.entity.Sim;
import com.example.webbansim.model.dto.Sim.SimDTO;
import com.example.webbansim.model.request.Sim.FindSimReq;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ISimService{

    List<SimDTO> getAllSim();
    SimDTO saveSim (SimDTO newSim);
    SimDTO getSimById(Long id);
    SimDTO updateSim(Sim oldSim);

    List<SimDTO> findBySo(FindSimReq sime);

    SimDTO findByIdSim(Long id);

    void deleteSimById(Long id);

    Page<SimDTO> findPaginated(int pageNo,int pageSize,String keyword);

}
