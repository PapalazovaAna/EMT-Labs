package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.model.enumerations.TemporaryReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TemporaryReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToMany
    private List<Accommodation> accommodations;

    @Enumerated(EnumType.STRING)
    private TemporaryReservationStatus status;

    public TemporaryReservation(){}

    public TemporaryReservation(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.accommodations = new ArrayList<>();
        this.status = TemporaryReservationStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public TemporaryReservationStatus getStatus() {
        return status;
    }

    public void setStatus(TemporaryReservationStatus status) {
        this.status = status;
    }
}
