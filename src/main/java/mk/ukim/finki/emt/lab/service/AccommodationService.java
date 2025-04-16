package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> save(AccommodationDto accommodation);
    Optional<Accommodation> update(Long id, AccommodationDto accommodation);
    void setRented(Long id);
    void deleteById(Long id);

    Optional<Object> findById(Long id);
}
