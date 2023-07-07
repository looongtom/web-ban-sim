package com.example.webbansim.model.request.Sim;

import lombok.Data;

@Data
public class FindSimReq {
    private String so;
    private Double price;
    private Integer idType;
}
