package com.example.webbansim.model.dto.Sim;

import com.example.webbansim.model.dto.NhaMang.NhaMangDTO;
import com.example.webbansim.service.INhaMangService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimDTO {
    @Autowired
    private INhaMangService iNhaMangService;
    private Long idSim;
    private String so;
    private String price;
    private Integer idType;
    private Integer idNm;
    private String tenNm;
    private String tenType;

    public void setPriceFromSim(Double priceDouble) {

        DecimalFormat decimalFormat = new DecimalFormat("#,##0 đ");
        String formattedValue = decimalFormat.format(priceDouble);

        this.price=formattedValue;
    }
}
