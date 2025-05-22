package com.project.jobApp.job;

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

import com.project.jobApp.exceptions.BadRequestException;

@RestController
@RequestMapping("/jobs") //defining baseURL for all the methods listed in this class.
public class JobController {
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}


	@GetMapping
//	@RequestMapping(value ="/jobs", method =RequestMethod.GET)
	public ResponseEntity<List<Job>> findAll(){ 
		return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable("id") Long id) {
		Job job = jobService.getJobById(id);
		if(job!=null) {
			return new ResponseEntity<>(job, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody JobDto jobDto) {
		if (jobDto.getCompanyId() == null) {
            throw new BadRequestException("Company ID is required");
        }
		try {
			jobService.createJob(jobDto);
			return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
		}catch(Exception e){
			throw new BadRequestException("Creating company failed. Try again!");
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJobById(@PathVariable("id") Long id){
		boolean x= jobService.deleteJobById(id);
		if(x) {
			return new ResponseEntity<>("Job Id: "+ id + " Deleted Successfully", HttpStatus.OK);	
		}
		return new ResponseEntity<>("Job with id "+ id + " not found", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJobById(@PathVariable("id")Long id, @RequestBody JobDto updatedJob){
		boolean updated = jobService.updateJobById(id, updatedJob);
		if(updated)
			return new ResponseEntity<>("Job id "+id+" updated succesfully", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
