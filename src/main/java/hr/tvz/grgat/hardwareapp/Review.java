package hr.tvz.grgat.hardwareapp;

import lombok.Data;

@Data
public class Review {

    private Rating rating;

    private String title;

    private String text;

    public Review(Rating rating, String title, String text) {
        this.rating = rating;
        this.title = title;
        this.text = text;
    }
}
