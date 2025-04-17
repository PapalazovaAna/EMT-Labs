package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Country;

import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
}
