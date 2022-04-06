package hr.tvz.grgat.hardwareapp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class HardwareCommand {

    @NotBlank(message = "Name must be entered")
    private String name;

    @NotBlank(message = "Code must be entered")
    private String code;

    @NotNull(message = "Price must be entered")
    @PositiveOrZero(message = "Number of remaining items must be entered as a positive integer")
    private Double price;

    @NotBlank(message = "Type must be entered")
    private String type;

    @NotNull(message = "Items remaining must be entered")
    @PositiveOrZero(message = "Number of remaining items must be entered as a positive integer")
    private Integer itemsRemaining;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public Integer getItemsRemaining() {
        return itemsRemaining;
    }

    /**
     * Creates a hardware object based on the entered input, and returns it
     */
    public Hardware getHardware() {
        return new Hardware(name, code, price, type, itemsRemaining);
    }

    @Override
    public String toString() {
        return "HardwareCommand{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", itemsRemaining=" + itemsRemaining +
                '}';
    }
}
