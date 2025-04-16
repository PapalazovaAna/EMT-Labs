package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.dto.AccommodationDto;
import mk.ukim.finki.emt.lab.dto.GuestDto;
import mk.ukim.finki.emt.lab.model.Guest;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.repository.GuestRepository;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import mk.ukim.finki.emt.lab.service.CountryService;
import mk.ukim.finki.emt.lab.service.GuestService;
import mk.ukim.finki.emt.lab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {
    private final HostService hostService;
    private final CountryService countryService;
    private final AccommodationService accommodationService;
    private final GuestRepository guestRepository;

    public GuestServiceImpl(HostService hostService, CountryService countryService, AccommodationService accommodationService, GuestRepository guestRepository) {
        this.hostService = hostService;
        this.countryService = countryService;
        this.accommodationService = accommodationService;
        this.guestRepository = guestRepository;
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return guestRepository.findById(id);
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> save(GuestDto guest) {
        return Optional.of(guestRepository.save(new Guest(guest.getName(), guest.getSurname(), guest.getCountry(), guest.getHosts())));
    }
}
