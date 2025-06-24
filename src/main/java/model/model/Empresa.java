package model.model;

public class Empresa {
    private int id;
    private String name;
    private String address;
    private String jobSector;
    private String description;

    public Empresa(int id, String name, String jobSector, String address, String description) {
        this.id = id;
        this.name = name;
        this.jobSector = jobSector;
        this.address = address;
        this.description = description;
    }
}
