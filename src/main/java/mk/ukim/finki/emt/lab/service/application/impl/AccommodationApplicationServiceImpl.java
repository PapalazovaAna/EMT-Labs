package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final CountryService countryService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, CountryService countryService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.countryService = countryService;
    }


    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.host());
        if(host.isPresent()){
            return accommodationService.save(accommodation.toAccommodation(host.get())).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.host());
        return accommodationService.update(id, accommodation.toAccommodation(host.orElse(null))).map(DisplayAccommodationDto::from);
    }

    @Override
    public void setRented(Long id) {
        accommodationService.setRented(id);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }
}
