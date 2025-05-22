package com.project.jobApp.companies;

import java.util.List;
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

import com.project.jobApp.job.Job;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	//find all companies
	@GetMapping
	public ResponseEntity<List<Company>> findAll(){
		return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
	}
	
	//find company by id - single
	@GetMapping("/id/{id}")
	public ResponseEntity<Company> findById(@PathVariable("id") Long id){
		Company company = companyService.getCompanyById(id);
		if(company!=null) {
			return new ResponseEntity<>(company, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	
	//find company by name - single
	@GetMapping("/name/{name}")
	public ResponseEntity<Company> findByName(@PathVariable("name") String name){
		Company company = companyService.getCompanyByName(name);
		if(company!=null)
			return new ResponseEntity<>(company, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//find companies by location - list
	@GetMapping("/{location}")
	public ResponseEntity<List<Company>> findByLocation(@PathVariable("location") String location){
		List<Company> companiesByLocation = companyService.findCompaniesByLocation(location);
		if(!companiesByLocation.isEmpty()) {
			return new ResponseEntity<>(companiesByLocation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//create company
	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody CompanyDto companyDto){
		companyService.createCompany(companyDto);
		return new ResponseEntity<>("Company created Succesfully", HttpStatus.CREATED);
	}
	
	//delete company by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable("id") Long id){
		boolean result = companyService.deleteCompanyById(id);
		if(result)
			return new ResponseEntity<>("Company deleted Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Company with this id Not found", HttpStatus.NOT_FOUND);
	}
	
	//update Company details
	@PutMapping
	public ResponseEntity<String> updateCompany(@RequestBody CompanyDto companyDto){
		boolean result = companyService.updateCompanyDetails(companyDto);
		if(result) {
			return new ResponseEntity<>("Company details Updated succesfully", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Company not found, so create a new company", HttpStatus.NOT_FOUND);	
		}
	}
	
	//pending testing-------------------------------------------
	//get jobs by company name/id
	@GetMapping("jobs/{name}")
	public ResponseEntity<List<Job>> getJobsByCompanyName(@PathVariable("name") String name){
		
		List<Job> jobsByCompany = companyService.getJobsByCompanyName(name);
		if(jobsByCompany!=null) {
			return new ResponseEntity<>(jobsByCompany, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//----------------------------------------------------
	
	
}