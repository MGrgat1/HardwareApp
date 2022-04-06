package hr.tvz.grgat.hardwareapp;

import lombok.Data;

@Data
public class Hardware {

    private String name;

    private String code;

    private Double price;

    private Type type;

    private Integer itemsRemaining;

    public Hardware(String name, String code, Double price, Type type, Integer itemsRemaining) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.itemsRemaining = itemsRemaining;
    }
}
