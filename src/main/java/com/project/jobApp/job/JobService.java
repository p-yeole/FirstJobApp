package com.project.jobApp.job;

import java.util.List;

public interface JobService {
	List<Job> findAll();
	Job getJobById(Long id);
	void createJob(JobDto jobDto);
	boolean deleteJobById(Long id);
	boolean updateJobById(Long id, JobDto updatedJob);
}
