package com.example.webbansim.service;

import com.example.webbansim.entity.Sim;
import com.example.webbansim.model.dto.Sim.SimDTO;
import com.example.webbansim.model.request.Sim.FindSimReq;

import java.util.List;


public interface ISimService{

    List<SimDTO> getAllSim();
    SimDTO saveSim (SimDTO newSim);
    SimDTO getSimById(Long id);
    SimDTO updateSim(Sim oldSim);

    List<SimDTO> findByScope(FindSimReq sime);

    SimDTO findByIdSim(Long id);

    void deleteSimById(Long id);

}
