package com.project.jobApp.companies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.jobApp.job.Job;
import com.project.jobApp.reviews.Review;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String about; //description
	private String sector; //IT, manufacturing, FMCG
	private Long totalEmployees; //to identify small, medium, large companies
	private List<String> locations; //all office locations
	
	@JsonIgnore
	@OneToMany
	private List<Review> reviews;
	
	@JsonBackReference//to not nest jobs inside it when jobs are created.
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)//mappedBy helps to not create extra table for relationshop between jobs and companies
	private List<Job> jobs; //job postings for this company
	
	//@OneToMany(mappedBy = "company"): Defines a one-to-many relationship where Job has a company field.
	//cascade = CascadeType.ALL: Ensures that saving/deleting a company affects related jobs.
	//orphanRemoval = true: Deletes jobs if they are removed from the jobs list.

	public Company(Long id, String name, String about, String sector, Long totalEmployees, List<String> locations,
			List<Review> reviews, List<Job> jobs) {
		super();
		this.id = id;
		this.name = name;
		this.about = about;
		this.sector = sector;
		this.totalEmployees = totalEmployees;
		this.locations = locations;
		this.reviews = reviews;
		this.jobs = jobs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Long getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(Long totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	

	
	
	
	
}
