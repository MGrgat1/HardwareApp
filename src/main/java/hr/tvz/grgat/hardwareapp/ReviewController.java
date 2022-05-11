package hr.tvz.grgat.hardwareapp;

import hr.tvz.grgat.hardwareapp.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/***
 * Implemented requests:
 * GET http://localhost:8080/reviews
 */
@RestController
@RequestMapping("reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/{code}")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@PathVariable final String code){
        return reviewService.findAllByHardwareCode(code);
    }

}
