package model.repository;

import java.util.List;
import java.util.Optional;

import model.models.Company;

public interface CompanyRepository {
    Company saveCompany(Company company);
    Optional<Company> findCompanyById(int id);
    List<Company> findAllCompanies();
    List<Company> findCompanyBySector(String sector);
    void deleteCompanyById(int id);
    Company updateCompanyInformation(Company company);
    List<Company> findCompanyByNameContaining(String name);
}
