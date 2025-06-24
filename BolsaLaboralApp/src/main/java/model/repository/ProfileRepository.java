package model.repository;
import model.models.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    Profile saveProfile(Profile profile);
    Optional<Profile> findByProfileId(int id);
    Optional<Profile> findByUserId(int userId);
    List<Profile> findAllProfiles();
    void deleteProfiles(int id);
    Profile updateProfile(Profile profile);
    List<Profile> findProfileByProfession(String profession);
    List<Profile> findProfileBySkills(List<String> skills);
}
