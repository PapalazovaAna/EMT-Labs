package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);
    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);
    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);
    void setRented(Long id);
    void deleteById(Long id);

}
