package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.service.application.CountryApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }
}
