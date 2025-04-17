package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enumerations.TemporaryReservationStatus;
import mk.ukim.finki.emt.lab.model.exceptions.AccommodationAlreadyInTemporaryReservationException;
import mk.ukim.finki.emt.lab.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.TemporaryReservationNotFoundException;
import mk.ukim.finki.emt.lab.repository.TemporaryReservationRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.TemporaryReservationService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, UserService userService, AccommodationService accommodationService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllAccommodationsInTemporaryReservation(Long temporaryReservationId) {
        if(temporaryReservationRepository.findById(temporaryReservationId).isEmpty())
            throw new TemporaryReservationNotFoundException(temporaryReservationId);
        return temporaryReservationRepository.findById(temporaryReservationId).get().getAccommodations();
    }

    @Override
    public Optional<TemporaryReservation> getActiveTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        return Optional.of(temporaryReservationRepository.findByUserAndStatus(user, TemporaryReservationStatus.CREATED)).orElseGet(() -> Optional.ofNullable(temporaryReservationRepository.save(new TemporaryReservation(user))));
    }

    @Override
    public Optional<TemporaryReservation> addAccommodationToTemporaryReservation(String username, Long accommodationId) {
        if (getActiveTemporaryReservation(username).isPresent()) {
            TemporaryReservation temporaryReservation = getActiveTemporaryReservation(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if (!temporaryReservation.getAccommodations().stream().filter(i -> i.getId().equals(accommodationId)).toList().isEmpty())
                throw new AccommodationAlreadyInTemporaryReservationException(accommodationId, username);
            temporaryReservation.getAccommodations().add(accommodation);
            return Optional.of(temporaryReservationRepository.save(temporaryReservation));
        }
        return Optional.empty();

    }

    @Override
    public void confirmReservations(Long userId) {
        User user = userService.findById(userId);
        TemporaryReservation reservation = temporaryReservationRepository
                .findByUserAndStatus(user, TemporaryReservationStatus.CREATED)
                .orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));

        for (Accommodation accommodation : reservation.getAccommodations()) {
            if (accommodation.isRented()) {
                throw new RuntimeException("Accommodation with ID " + accommodation.getId() + " is already rented.");
            }
        }

        for (Accommodation accommodation : reservation.getAccommodations()) {
            accommodation.setRented(true);
        }

        reservation.setStatus(TemporaryReservationStatus.FINISHED);
        temporaryReservationRepository.save(reservation);
    }
}
