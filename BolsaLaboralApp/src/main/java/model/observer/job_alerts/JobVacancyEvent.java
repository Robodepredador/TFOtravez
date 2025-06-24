package model.observer.job_alerts;
import model.models.*;

import java.time.LocalDate;

public class JobVacancyEvent {
    private final JobVacancy vacancy;
    private final LocalDate postingDate;

    public JobVacancyEvent(JobVacancy vacancy) {
        this.vacancy = vacancy;
        this.postingDate = LocalDate.now();
    }

    // Getters
    public JobVacancy getVacancy() { return vacancy; }
    public LocalDate getPostingDate() { return postingDate; }
}
