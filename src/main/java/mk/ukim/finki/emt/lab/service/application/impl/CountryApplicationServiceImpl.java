package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.views.HostsByCountryView;
import mk.ukim.finki.emt.lab.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.emt.lab.service.application.CountryApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    public CountryApplicationServiceImpl(CountryService countryService, HostsPerCountryViewRepository hostsPerCountryViewRepository) {
        this.countryService = countryService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
    }


    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public List<HostsByCountryView> findAllHostsPerCountry() {
        return hostsPerCountryViewRepository.findAll();
    }

    @Override
    public HostsByCountryView findHostsPerCountry(Long id) {
        return hostsPerCountryViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        hostsPerCountryViewRepository.refreshMaterializedView();
    }

}
