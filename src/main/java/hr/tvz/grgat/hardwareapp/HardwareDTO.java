package hr.tvz.grgat.hardwareapp;

import lombok.Data;

@Data
public class HardwareDTO {

    private String code;
    private String name;
    private Double price;


    public HardwareDTO(String code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
