package com.example.companyms.companies;

import java.util.List;

public interface CompanyService {
    List<Companies> findCompanies();
    String createCompany(Companies company);
    String deleteCompany(int id);
    Companies getCompanyById(int id);
    boolean updateCompanyById(int id, Companies updateCompany);
    public void updateCompanyByRating(dtoReview review);
}
