package com.example.webbansim.model.dto.Sim;


import com.example.webbansim.entity.Sim;

public class SimMapper {
    public static SimDTO toSimDTO (Sim sim){
        SimDTO simDTO = new SimDTO();
        simDTO.setPriceFromSim(sim.getPrice());
        return simDTO
                .setIdSim(sim.getIdSim())
                .setSo(sim.getSo())
                .setIdType(sim.getIdType())
                .setIdNm(sim.getIdNm())
                ;
    }

    public static Sim toSim(SimDTO simDTO){
        return new Sim()
                .setIdSim(simDTO.getIdSim())
                .setSo(simDTO.getSo())
                .setPrice(Double.valueOf(simDTO.getPrice()))
                .setIdType(simDTO.getIdType())
                .setIdNm(simDTO.getIdNm());
    }
}
