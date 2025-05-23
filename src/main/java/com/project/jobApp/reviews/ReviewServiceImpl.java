package com.project.jobApp.reviews;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.jobApp.companies.Company;
import com.project.jobApp.companies.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Review> findByCompanyId(Long companyId) {

		List<Review> reviews = reviewRepository.findByCompany_Id(companyId);
		return reviews;
	}
	
	@Override
	@Transactional
	public boolean postReview(ReviewDto reviewDto, Long companyId) {
		
		Optional<Company> company = companyRepository.findById(companyId);
		if(company.isEmpty()) {
			return false;
		}
		Review review = new Review();
		review.setComment(reviewDto.getComment());
		review.setCompany(company.get());
		reviewRepository.save(review);
		return true;
	}
	
	//find review by companyId and reviewId
	@Override
	public Optional<Review> findByCompanyIdAndReviewId(Long companyId, Long reviewId) {
		
		return reviewRepository.findByCompany_IdAndId(companyId, reviewId);
	}

	//edit a review by companyId and reviewId
	@Override
	public boolean updateReview(Long companyId, Long reviewId, ReviewDto reviewDto) {
		// TODO Auto-generated method stub
		Optional<Review> reviewOptional= reviewRepository.findByCompany_IdAndId(companyId, reviewId);
		if(reviewOptional.isEmpty()) {
			return false;
		}
		else {
			Review review = reviewOptional.get();
			review.setComment(reviewDto.getComment());
			reviewRepository.save(review);
			return true;
		}
	}
	
	//delete a review
	@Override
	@Transactional
	public void deleteReview(Long companyId, Long reviewId) {
		reviewRepository.deleteByCompany_IdAndId(companyId, reviewId);
	}

}
