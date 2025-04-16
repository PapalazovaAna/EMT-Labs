package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.dto.AccommodationDto;
import mk.ukim.finki.emt.lab.dto.GuestDto;
import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.Guest;
import mk.ukim.finki.emt.lab.model.Host;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    Optional<Guest> findById(Long id);
    List<Guest> findAll();
    Optional<Guest> save(GuestDto guest);
//    Optional<Guest> update(Long id, AccommodationDto accommodation);
//    void deleteById(Long id);


}
