package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.enumerations.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Long id, Accommodation accommodation);
    void setRented(Long id);
    void deleteById(Long id);

    Optional<Accommodation> findById(Long id);

    HashMap<Category, Integer> statisticsForCategories();
}
