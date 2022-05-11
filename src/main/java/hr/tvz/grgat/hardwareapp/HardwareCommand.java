package hr.tvz.grgat.hardwareapp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class HardwareCommand {

    @NotBlank(message = "Code must be entered")
    private String code;

    @NotBlank(message = "Name must be entered")
    private String name;

    @NotNull(message = "Price must be entered")
    @PositiveOrZero(message = "Number of remaining items must be entered as a positive integer")
    private Double price;

    @NotNull(message = "Type must be entered")
    private Type type;

    @NotNull(message = "Items remaining must be entered")
    @PositiveOrZero(message = "Number of remaining items must be entered as a positive integer")
    private Integer itemsRemaining;

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

    public Integer getItemsRemaining() {
        return itemsRemaining;
    }

    /**
     * Creates a hardware object based on the entered input, and returns it
     */
    public Hardware getHardware() {
        return new Hardware(code, name, price, type, itemsRemaining);
    }



    @Override
    public String toString() {
        return "HardwareCommand{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", itemsRemaining=" + itemsRemaining +
                '}';
    }
}
