package mk.ukim.finki.emt.lab.dto;

import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.enumerations.TemporaryReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record TemporaryReservationDto(
        Long id,
        LocalDateTime dateCreated,
        DisplayUserDto user,
        List<DisplayAccommodationDto> accommodations,
        TemporaryReservationStatus status
) {
    public static TemporaryReservationDto from(TemporaryReservation temporaryReservation){
        return new TemporaryReservationDto(
                temporaryReservation.getId(),
                temporaryReservation.getDateCreated(),
                DisplayUserDto.from(temporaryReservation.getUser()),
                DisplayAccommodationDto.from(temporaryReservation.getAccommodations()),
                temporaryReservation.getStatus()
        );
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public DisplayUserDto getUser() {
        return user;
    }

    public List<DisplayAccommodationDto> getAccommodations() {
        return accommodations;
    }

    public TemporaryReservationStatus getStatus() {
        return status;
    }
}
