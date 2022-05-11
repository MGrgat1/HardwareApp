package hr.tvz.grgat.hardwareapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewJpaRepository reviewJpaRepository;

    public ReviewServiceImpl(ReviewJpaRepository reviewJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewJpaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByHardwareCode(String code) {
        return reviewJpaRepository.findAllByHardware_Code(code).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ReviewDTO mapToDTO(final Review review) {
        return new ReviewDTO(review.getRating(), review.getTitle(), review.getText());
    }
}
