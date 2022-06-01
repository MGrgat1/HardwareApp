package hr.tvz.grgat.hardwareapp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class HardwareCommand {


    @NotBlank(message = "Code must be entered")
    private String code;

    @NotBlank(message = "Name must be entered")
    private String name;

    @NotNull(message = "Type must be entered")
    private Type type;

    @NotNull(message = "Price must be entered")
    @PositiveOrZero(message = "Number of remaining items must be entered as a positive integer")
    private Double price;


    @NotNull(message = "Stock must be entered")
    @PositiveOrZero(message = "Stock must be entered as a positive integer")
    private Integer stock;


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    /**
     * Creates a hardware object based on the entered input, and returns it
     */
    public Hardware getHardware() {
        return new Hardware(code, name, price, type, stock);
    }

    public HardwareCommand(String code, String name, Type type, Double price, Integer stock) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "HardwareCommand{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", stock=" + stock +
                '}';
    }
}
