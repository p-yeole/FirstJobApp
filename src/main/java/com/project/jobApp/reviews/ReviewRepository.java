package com.project.jobApp.reviews;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long>{

	//custom method to find reviews by companyId, JPA takes care of implementations of this method.
	List<Review> findByCompany_Id(Long companyId);
	Optional<Review> findByCompany_IdAndId(Long companyId, Long reviewId);
	void deleteByCompany_IdAndId(Long companyId, Long reviewId);
}
