package com.project.jobApp.job;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobApp.companies.Company;
import com.project.jobApp.companies.CompanyRepository;
import com.project.jobApp.exceptions.BadRequestException;

import jakarta.transaction.Transactional;

@Service
public class JobServiceImpl implements JobService{

	// private List<Job> jobs = new ArrayList<>();
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	@Transactional  // Ensures atomicity
//	Begins a new transaction before the method runs.
//	Commits the transaction if the method completes successfully.
//	Rolls back the transaction if a RuntimeException or Error is thrown.
    public void createJob(JobDto jobDto) {
		Long companyId = jobDto.getCompanyId();
        
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BadRequestException("Company with id:" + companyId + " does not exist. Please enter appropriate companyId."));

        Job job = new Job();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        job.setMinSalary(jobDto.getMinSalary());
        job.setMaxSalary(jobDto.getMaxSalary());
        job.setCompany(company); // setting only the company reference

        jobRepository.save(job);
//        return jobDto;
    }

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJobById(Long id) {
		Optional<Job> jobExists = jobRepository.findById(id);
		if(jobExists.isPresent()) {
			jobRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateJobById(Long id, JobDto updatedJob) {
	    Optional<Job> existingJobOptional = jobRepository.findById(id);
	    
	    if (existingJobOptional.isPresent()) {
	        Job existingJob = existingJobOptional.get();
	        existingJob.setTitle(updatedJob.getTitle());
	        existingJob.setDescription(updatedJob.getDescription());
	        existingJob.setMinSalary(updatedJob.getMinSalary());
	        existingJob.setMaxSalary(updatedJob.getMaxSalary());
	        existingJob.setLocation(updatedJob.getLocation());
	        
	        jobRepository.save(existingJob);
	        return true;
	    }
	    
	    return false;
	}

	 
}
