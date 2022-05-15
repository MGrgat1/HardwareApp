package hr.tvz.grgat.hardwareapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review,Long> {

        //List<Review> findAll();

        List<Review> findAllByHardware_Code(String code);


        List<Review> findAllByTextContaining(String textSnippet);

}
