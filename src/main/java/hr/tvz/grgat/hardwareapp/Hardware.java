package hr.tvz.grgat.hardwareapp;

import lombok.Data;

@Data
public class Hardware {

    private String code;

    private String name;

    private Double price;

    private Type type;

    private Integer itemsRemaining;

    public Hardware(String code, String name, Double price, Type type, Integer itemsRemaining) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.itemsRemaining = itemsRemaining;
    }
}
