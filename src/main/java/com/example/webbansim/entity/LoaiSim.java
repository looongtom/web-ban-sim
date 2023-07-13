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

@Entity
@Table(name = "typesim")
@Data
public class LoaiSim {
    @Id
    @Column(name = "id_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idType;
    @Column(name = "ten_type")
    private String tenType;
}
