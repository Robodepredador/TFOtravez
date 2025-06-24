package model.observer.job_alerts;

public interface JobAlertObserver {
    void onNewJobPosted(JobVacancyEvent event);
}
