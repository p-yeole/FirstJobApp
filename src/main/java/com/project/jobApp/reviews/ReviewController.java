package com.project.jobApp.reviews;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies/{companyId}/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	//get all reviews of a company
	@GetMapping
	public ResponseEntity<List<Review>> findAll(@PathVariable("companyId") Long companyId){
		List<Review> companyReviews =  reviewService.findByCompanyId(companyId);
		if (companyReviews.isEmpty()) {
		    return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
		}
		return new ResponseEntity<>(companyReviews, HttpStatus.OK);
	}
	
	//create Review for a specific company by companyId
	@PostMapping
	public ResponseEntity<String> postReview(@RequestBody ReviewDto reviewDto, @PathVariable Long companyId){
		if(reviewDto.getComment().isEmpty()) {
			return new ResponseEntity<>("Review cannot be empty.", HttpStatus.BAD_REQUEST);
		}
		boolean result = reviewService.postReview(reviewDto, companyId);
		if(result) {
			return new ResponseEntity<>("Review posted successfully.", HttpStatus.CREATED);	
		}
		else {
			return new ResponseEntity<>("No such company exists. Please check the companyId once.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//get review by companyId and reviewId
	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> findById(@PathVariable("companyId") Long companyId, @PathVariable("reviewId") Long reviewId){
		Optional<Review> review = reviewService.findByCompanyIdAndReviewId(companyId, reviewId);
		if(review.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(review.get(), HttpStatus.OK);
		
	}
	
	//edit review by review id and company id
	@PutMapping("/{reviewId}")
	public ResponseEntity<String> editReview(@RequestBody ReviewDto reviewDto, @PathVariable("companyId") Long companyId, @PathVariable("reviewId") Long reviewId){
		if(reviewDto.getComment().isEmpty()) {
			return new ResponseEntity<>("Review cannot be empty.", HttpStatus.BAD_REQUEST);
		}
		boolean result = reviewService.updateReview(companyId, reviewId, reviewDto);
		if(result) {
			return new ResponseEntity<>("Review updated successfully.", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Company/Review with given id does not exists. Please check the companyId/reviewId once.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//delete a review
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable("companyId") Long companyId, @PathVariable("reviewId") Long reviewId) {
		reviewService.deleteReview(companyId, reviewId);
		return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
	}
	
}
