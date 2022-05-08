package hr.tvz.grgat.hardwareapp;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static hr.tvz.grgat.hardwareapp.Type.CPU;
import static hr.tvz.grgat.hardwareapp.Type.LAPTOP;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository{

    private List<Review> reviews = new ArrayList<Review>(){{
        add(new Review(Rating.FIVE_STAR, "Savršeno za Roblox", "Napokon mogu uživati u Robloxu u 1440p kvaliteti"));
        add(new Review(Rating.TWO_STAR, "Nije dobro", "Mogli su to bolje napraviti"));
        add(new Review(Rating.FOUR_STAR, "Razočaran sam", "Presporo se učitava"));
        add(new Review(Rating.THREE_STAR, "Tak-tak", "Nit smrdi nit miriše"));
        add(new Review(Rating.FIVE_STAR, "Predivno", "Slobodno ovo kupite"));
        add(new Review(Rating.ONE_STAR, "Užasno", "Nemojte ovo kupiti"));
    }};

    @Override
    public List<Review> findAll() {
        return reviews;
    }
}
