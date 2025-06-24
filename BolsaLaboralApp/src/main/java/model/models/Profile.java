package model.models;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private int id;
    private int userId;
    private String profession;
    private List<String> jobSkills;
    private String description;
    private List<ExperienciaLaboral> experiencias;


    public Profile(int id, int user_id, String profession, List<String> jobSkills, String description) {
        this.id = id;
        this.userId = user_id;
        this.profession = profession;
        this.jobSkills = jobSkills != null ? jobSkills : new ArrayList<>();
        this.description = description;
        this.experiencias = new ArrayList<>(); // Inicialización añadida
    }

    public void addSkill(String skill) {
        if (skill == null || skill.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill no puede estar vacía");
        }
        if (!jobSkills.contains(skill)) {
            jobSkills.add(skill);
        }
    }

    public boolean hasSkill(String skill) {
        return jobSkills.contains(skill);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<String> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(List<String> jobSkills) {
        this.jobSkills = jobSkills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExperienciaLaboral> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<ExperienciaLaboral> experiencias) {
        this.experiencias = experiencias;
    }

    public void addExperiencia(ExperienciaLaboral experiencia) {
        if (experiencia != null) {
            this.experiencias.add(experiencia);
        }
    }
}
