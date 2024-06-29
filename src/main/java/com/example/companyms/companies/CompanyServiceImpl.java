package com.example.companyms.companies;

import com.example.companyms.companies.clients.ReviewClient;
import feign.FeignException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    CompanyRepository companyRepository;
    ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository,ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Companies> findCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public String createCompany(Companies company) {
        try {
            companyRepository.save(company);
            return "Company created Succesfully";
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String deleteCompany(int id) {
        try {
            companyRepository.deleteById(id);
            return "Company deleted Succesfully";
        }catch (Exception e) {
            return "";
        }
    }

    @Override
    public Companies getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompanyById(int id, Companies updateCompany) {
        Optional<Companies> companiesOptional = companyRepository.findById(id);
        if(companiesOptional.isPresent()) {
            Companies companies = companiesOptional.get();
            companies.setName(updateCompany.getName());
            companies.setDescription(updateCompany.getDescription());
            companyRepository.save(companies);
            return true;
        }
        return false;
    }

    @Override
    public void updateCompanyByRating(dtoReview dr) {
        System.out.println(dr.getComment());
        Companies company = companyRepository.findById(dr.getCompanyId()).orElseThrow(() -> new NotFoundException("Company not found" + dr.getCompanyId()));
        double rating = reviewClient.getAverageRatingByCompany(dr.getCompanyId());
        company.setRating((int) rating);
        companyRepository.save(company);
    }

}
