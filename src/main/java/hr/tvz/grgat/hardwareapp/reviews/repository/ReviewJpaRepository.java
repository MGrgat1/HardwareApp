package hr.tvz.grgat.hardwareapp.reviews.repository;

import hr.tvz.grgat.hardwareapp.reviews.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review,Long> {

        //List<Review> findAll();

        List<Review> findAllByHardware_Code(String code);


        List<Review> findAllByTextContaining(String textSnippet);

}
