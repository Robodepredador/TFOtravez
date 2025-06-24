package model.observer.postulation;
import model.models.*;
import model.util.PostulationStatus;

public class PostulationEvent {
    private final Postulation postulation;
    private final PostulationStatus previousStatus;

    public PostulationEvent(Postulation postulation, PostulationStatus previousStatus) {
        this.postulation = postulation;
        this.previousStatus = previousStatus;
    }

    // Getters
    public Postulation getPostulation() { return postulation; }
    public PostulationStatus getPreviousStatus() { return previousStatus; }
}
