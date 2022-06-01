package hr.tvz.grgat.hardwareapp;

import hr.tvz.grgat.hardwareapp.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);

    List<ReviewDTO> findAllByTextSnippet(String textSnippet);
}
