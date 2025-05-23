package com.project.jobApp.reviews;

public class ReviewDto {
	
	private String comment;

	public ReviewDto(String comment) {
		super();
		this.comment = comment;
	}

	public ReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
