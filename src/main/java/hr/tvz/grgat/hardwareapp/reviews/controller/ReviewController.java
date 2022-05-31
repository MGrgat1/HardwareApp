package hr.tvz.grgat.hardwareapp.reviews.controller;

import hr.tvz.grgat.hardwareapp.reviews.dto.ReviewDTO;
import hr.tvz.grgat.hardwareapp.reviews.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * Implemented requests:
 * GET http://localhost:8080/reviews
 */
@RestController
@RequestMapping("review")
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


    @GetMapping(params = "hardwareCode")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam final String hardwareCode){
        return reviewService.findAllByHardwareCode(hardwareCode);
    }

    /*
    @GetMapping("/text/{text}")
    public List<ReviewDTO> getAllReviewsByTextSnippet(@PathVariable final String text){
        return reviewService.findAllByTextSnippet(text);
    }
     */

}
