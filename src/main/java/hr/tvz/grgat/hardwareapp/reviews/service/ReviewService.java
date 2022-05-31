package hr.tvz.grgat.hardwareapp.reviews.service;

import hr.tvz.grgat.hardwareapp.reviews.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);

    List<ReviewDTO> findAllByTextSnippet(String textSnippet);
}
