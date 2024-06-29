package com.example.companyms.companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Companies>> findCompanies() {
        return ResponseEntity.ok(companyService.findCompanies());
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Companies company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company added Sucessfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Companies> companyById(@PathVariable int id) {
        Companies company = companyService.getCompanyById(id);
        if(company !=null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        String company = companyService.deleteCompany(id);
        if(!Objects.equals(company, "")) {
            return  new ResponseEntity<>("Company deleted succesfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Integer id, @RequestBody Companies updatedCompany) {
        companyService.updateCompanyById(id,updatedCompany);
        return new ResponseEntity<>("Company Updated succesfully", HttpStatus.OK);
    }

}
