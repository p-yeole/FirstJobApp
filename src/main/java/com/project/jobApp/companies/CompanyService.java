package com.project.jobApp.companies;

import java.util.List;

import com.project.jobApp.job.Job;

public interface CompanyService {

	List<Company> findAll();
	Company getCompanyById(Long id);
	Company getCompanyByName(String name);
	List<Company> findCompaniesByLocation(String location);
	void createCompany(CompanyDto companyDto);
	boolean deleteCompanyById(Long id);
	boolean updateCompanyDetails(CompanyDto companyDto);
	List<Job> getJobsByCompanyName(String name);
}
