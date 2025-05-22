package com.project.jobApp.job;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.jobApp.companies.Company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Job {
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Job(Long id, String title, String description, String minSalary, String maxSalary, String location, Company company) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.location = location;
		this.company = company;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id =1L;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
	
	@JsonManagedReference //to go forward to company from jobs.
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getMinSalary() {
		return minSalary;
	}


	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}


	public String getMaxSalary() {
		return maxSalary;
	}


	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}

	
	
	
}
