package com.example.webbansim.model.dto.Sim;

import com.example.webbansim.entity.Sim;

public class SimMapper {
    public static SimDTO toSimDTO (Sim sim){
        return new SimDTO()
                .setIdSim(sim.getIdSim())
                .setSo(sim.getSo())
                .setPrice(sim.getPrice())
                .setIdType(sim.getIdType())
                .setIdNm(sim.getIdNm());
    }

    public static Sim toSim(SimDTO simDTO){
        return new Sim()
                .setIdSim(simDTO.getIdSim())
                .setSo(simDTO.getSo())
                .setPrice(simDTO.getPrice())
                .setIdType(simDTO.getIdType())
                .setIdNm(simDTO.getIdNm());
    }
}
