package model.builders;
import model.models.*;

import java.time.LocalDate;
import java.util.List;

public interface JobVacancyBuilder {
    JobVacancyBuilder reset();

    JobVacancyBuilder withId(int id);
    JobVacancyBuilder withTitle(String title);
    JobVacancyBuilder withDescription(String description);
    JobVacancyBuilder withCompany(Company company);
    JobVacancyBuilder withPublicationDate(LocalDate date);
    JobVacancyBuilder withRequirements(List<String> requirements);
    JobVacancyBuilder addRequirement(String requirement);
    JobVacancyBuilder withSalary(double salary);

    JobVacancy build();
}
