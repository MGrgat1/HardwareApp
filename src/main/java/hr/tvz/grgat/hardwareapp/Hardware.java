package hr.tvz.grgat.hardwareapp;

import lombok.Data;

@Data
public class Hardware {

    private long id;

    private String code;

    private String name;

    private Double price;

    private Type type;

    private Integer itemsRemaining;

    public Hardware(long id, String code, String name, Double price, Type type, Integer itemsRemaining) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.itemsRemaining = itemsRemaining;
    }

    public void setId(long id) {
        this.id = id;
    }
}
