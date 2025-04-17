package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.dto.TemporaryReservationDto;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.TemporaryReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    Optional<TemporaryReservation> findByUserAndStatus(User user, TemporaryReservationStatus status);
}
