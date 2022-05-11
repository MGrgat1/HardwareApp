package hr.tvz.grgat.hardwareapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review,Long> {

        public List<Review> findAll();

        public List<Review> findAllByHardware_Code(String code);

}
