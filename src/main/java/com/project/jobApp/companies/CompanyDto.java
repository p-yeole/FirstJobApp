package com.project.jobApp.companies;

import java.util.List;

public class CompanyDto {

	private String name;
	private String about; //description
	private String sector; //IT, manufacturing, FMCG
	private Long totalEmployees; //to identify small, medium, large companies
	private List<String> locations; //all office locations
	
	public CompanyDto(String name, String about, String sector, Long totalEmployees, List<String> locations) {
		super();
		this.name = name;
		this.about = about;
		this.sector = sector;
		this.totalEmployees = totalEmployees;
		this.locations = locations;
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
	
	
}
