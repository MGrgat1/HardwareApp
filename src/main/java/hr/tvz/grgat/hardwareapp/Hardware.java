package hr.tvz.grgat.hardwareapp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="HARDWARE")
public class Hardware implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private long id;

    @Column(name= "CODE")
    private String code;

    @Column(name= "HARDWARE_NAME")
    private String name;

    @Column(name= "PRICE")
    private Double price;

    @Column(name= "HARDWARE_TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name= "ITEMS_REMAINING")
    private Integer itemsRemaining;


    /**
     * Defines the mapping between hardware and review classes.
     * The argument mappedBy="hardware" references the following mapping in hardwareapp.Review:
     *     @ManyToOne
     *     @JoinColumn(name="HARDWARE_ID")
     *     private Hardware hardware;
     */

    @OneToMany(mappedBy="hardware", fetch= FetchType.EAGER)
    private List<Review> reviewList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * The default constructor used by Hibernate
     */
    public Hardware() {
    }

    /**
     * The hardware constructor used in HardwareJdbcRepository
     */
    public Hardware(long id, String code, String name, Double price, Type type, Integer itemsRemaining) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.itemsRemaining = itemsRemaining;
    }

    /**
     * The hardware constructor used in HardwareCommand
     */
    public Hardware(String code, String name, Double price, Type type, Integer itemsRemaining) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.itemsRemaining = itemsRemaining;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getItemsRemaining() {
        return itemsRemaining;
    }

    public void setItemsRemaining(Integer itemsRemaining) {
        this.itemsRemaining = itemsRemaining;
    }
}
