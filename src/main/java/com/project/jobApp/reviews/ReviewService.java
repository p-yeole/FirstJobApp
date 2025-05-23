package com.project.jobApp.reviews;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
	
	//get all reviews of a company
	List<Review> findByCompanyId(Long companyId);
	
	//create review
	boolean postReview(ReviewDto reviewDto, Long companyId);
	
	// get specific review
	Optional<Review> findByCompanyIdAndReviewId(Long companyId, Long reviewId);

	//update review
	boolean updateReview(Long companyId, Long reviewId, ReviewDto reviewDto);

	//deleting a review
	void deleteReview(Long companyId, Long reviewId);


	
}
