package hr.tvz.grgat.hardwareapp;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);
}
