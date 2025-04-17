package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.dto.TemporaryReservationDto;
import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    List<DisplayAccommodationDto> listAllAccommodationsInTemporaryReservation(Long temporaryReservationId);
    Optional<TemporaryReservationDto> getActiveTemporaryReservation(String username);
    Optional<TemporaryReservationDto> addAccommodationToTemporaryReservation(String username, Long accommodationId);
    void confirmReservations(Long userId);

}
