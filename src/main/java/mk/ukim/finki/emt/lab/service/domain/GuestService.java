package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.dto.GuestDto;
import mk.ukim.finki.emt.lab.model.domain.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    Optional<Guest> findById(Long id);
    List<Guest> findAll();
    Optional<Guest> save(GuestDto guest);
//    Optional<Guest> update(Long id, AccommodationDto accommodation);
//    void deleteById(Long id);


}
