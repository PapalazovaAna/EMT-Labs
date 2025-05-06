package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final CountryService countryService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, CountryService countryService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.countryService = countryService;
    }


    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if(accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()){
            return Optional.of(accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(), hostService.findById(accommodation.getHost().getId()).get(), accommodation.getNumRooms(), accommodation.isRented())));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public HashMap<Category, Integer> statisticsForCategories() {
        HashMap<Category, Integer> statistics = new HashMap<>();
        for (Accommodation accommodation: accommodationRepository.findAll()) {
            if(statistics.containsKey(accommodation.getCategory())){
                statistics.replace(accommodation.getCategory(), statistics.get(accommodation.getCategory()), statistics.get(accommodation.getCategory()) + 1);
            }
            else{
                statistics.put(accommodation.getCategory(), 1);
            }
        }
        return statistics;
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
                        existingAccommodation.setHost(hostService.findById(accommodation.getHost().getId()).get());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    existingAccommodation.setRented(accommodation.isRented());
                    return accommodationRepository.save(existingAccommodation);
                });

    }

    @Override
    public void setRented(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow();
        accommodation.setRented(true);
        accommodationRepository.save(accommodation);
    }
}
