package hr.tvz.grgat.hardwareapp.reviews.review;

import hr.tvz.grgat.hardwareapp.hardware.hardware.Hardware;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="REVIEWS")
public class Review implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private long id;

    @Column(name = "RATING")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name="HARDWARE_ID")
    private Hardware hardware;

    /**
     * The default constructor used by Hibernate
     */
    public Review() {
    }

    public Review(Rating rating, String title, String text) {
        this.rating = rating;
        this.title = title;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
