package com.example.webbansim.model.request.Sim;


import lombok.Data;

@Data
public class CreateSimReq {
    private String so;
    private Double price;
    private Integer idType;
}
