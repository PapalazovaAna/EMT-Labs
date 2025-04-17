package mk.ukim.finki.emt.lab.service.application.impl;


import mk.ukim.finki.emt.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.dto.TemporaryReservationDto;
import mk.ukim.finki.emt.lab.service.application.TemporaryReservationApplicationService;
import mk.ukim.finki.emt.lab.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {
    private final TemporaryReservationService temporaryReservationService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationService temporaryReservationService) {
        this.temporaryReservationService = temporaryReservationService;
    }

    @Override
    public List<DisplayAccommodationDto> listAllAccommodationsInTemporaryReservation(Long temporaryReservationId) {
        return temporaryReservationService.listAllAccommodationsInTemporaryReservation(temporaryReservationId).stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<TemporaryReservationDto> getActiveTemporaryReservation(String username) {
        return temporaryReservationService.getActiveTemporaryReservation(username).map(TemporaryReservationDto::from);
    }

    @Override
    public Optional<TemporaryReservationDto> addAccommodationToTemporaryReservation(String username, Long accommodationId) {
        return temporaryReservationService.addAccommodationToTemporaryReservation(username, accommodationId).map(TemporaryReservationDto::from);
    }

    @Override
    public void confirmReservations(Long userId) {
        temporaryReservationService.confirmReservations(userId);
    }
}
