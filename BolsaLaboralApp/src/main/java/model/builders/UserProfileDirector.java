package model.builders;
import model.models.User;

import java.sql.Connection;
import java.util.List;

public class UserProfileDirector {

    public UserProfileDirector(Connection conn) {
    }

    public User createStandardCandidate(ProfileBuilder builder, int id, String username, String email, String profession) {
        return builder.reset()
                .withUserBasicInfo(id, username, email)
                .withProfession(profession)
                .withDescription("")
                .addSkill("Communication")
                .buildUserWithProfile();
    }

    public User createTechCandidate(ProfileBuilder builder, int id, String username, String email, List<String> techSkills) {
        return builder.reset()
                .withUserBasicInfo(id, username, email)
                .withProfession("Software Developer")
                .withSkills(techSkills)
                .withDescription("Experienced in modern technologies")
                .buildUserWithProfile();
    }
}
