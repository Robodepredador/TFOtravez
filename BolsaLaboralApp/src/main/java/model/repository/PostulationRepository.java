package model.repository;
import model.models.*;
import model.util.PostulationStatus;

import java.util.List;
import java.util.Optional;

public interface PostulationRepository {
    Postulation savePostulation(Postulation postulation);
    Optional<Postulation> findPostulationById(int id);
    List<Postulation> findPostulationByUser(User user);
    List<Postulation> findPostulationByJobVacancy(int jobVacancyId);
    List<Postulation> findPostulationByStatus(PostulationStatus status);
    void updatePostulationStatus(int postulationId, PostulationStatus status);
    boolean existsPostulationByUserAndJobVacancy(User user, int jobVacancyId);
    void deletePostulation(int id);
}
