package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    List<Accommodation> listAllAccommodationsInTemporaryReservation(Long temporaryReservationId);
    Optional<TemporaryReservation> getActiveTemporaryReservation(String username);
    Optional<TemporaryReservation> addAccommodationToTemporaryReservation(String username, Long accommodationId);
    void confirmReservations(Long userId);
}
