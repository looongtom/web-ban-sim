package com.example.webbansim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
@Entity
@Table(name = "sim")
public class Sim  {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_sim")
    private Long idSim;
    private String so;
    @Column(name = "price_sim")
    private Double price;
    @Column(name = "id_type")
    private Integer idType;
    @Column(name = "id_nm")
    private Integer idNm;


}
