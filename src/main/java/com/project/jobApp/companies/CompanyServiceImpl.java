package com.project.jobApp.companies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobApp.job.Job;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company getCompanyById(Long id) {
	    return this.findAll().stream()
	        .filter(company -> company.getId().equals(id))
	        .findFirst()
	        .orElse(null);
	}


	@Override
	public Company getCompanyByName(String name) {
		// TODO Auto-generated method stub
		return this.findAll().stream().filter(company -> company.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	@Override
	public List<Company> findCompaniesByLocation(String location) {
		List<Company> allCompanies = this.findAll();
		List<Company> getCompaniesByLocation = new ArrayList<>();
		
		for(Company company: allCompanies) {
			if(company.getLocations().contains(location)) {
				getCompaniesByLocation.add(company); 
			}
		}
		return getCompaniesByLocation;
	}

	@Override
	public void createCompany(CompanyDto companyDto) {
		
		Company company = new Company();
		
		company.setName(companyDto.getName());
		company.setAbout(companyDto.getAbout());
		company.setSector(companyDto.getSector());
		company.setTotalEmployees(companyDto.getTotalEmployees());
		company.setLocations(companyDto.getLocations());
		
		companyRepository.save(company);
	}

	@Override
	public boolean deleteCompanyById(Long id) {
		if(this.getCompanyById(id)!=null){
			companyRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCompanyDetails(CompanyDto companyDto) {
		Company oldCompanyDetails = this.getCompanyByName(companyDto.getName());
		if(oldCompanyDetails!=null) {
			oldCompanyDetails.setName(companyDto.getName());
			oldCompanyDetails.setAbout(companyDto.getAbout());
			oldCompanyDetails.setLocations(companyDto.getLocations());
			oldCompanyDetails.setSector(companyDto.getSector());
			oldCompanyDetails.setTotalEmployees(companyDto.getTotalEmployees());
			companyRepository.save(oldCompanyDetails);
			return true;
		}
		return false;
		
		
	}

	@Override
	public List<Job> getJobsByCompanyName(String name) {
		// TODO Auto-generated method stub
		if(this.getCompanyByName(name).getJobs().isEmpty()) {
			return null;
		}
		return this.getCompanyByName(name).getJobs();
	}



}
