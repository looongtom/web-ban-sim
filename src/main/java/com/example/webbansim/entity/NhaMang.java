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
@Table(name = "nhamang")
public class NhaMang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_nm")
    private Integer idNm;
    @Column(name = "ten_nm")
    private String tenNm;
}
