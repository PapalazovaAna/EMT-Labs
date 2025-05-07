package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.CreateHostDto;
import mk.ukim.finki.emt.lab.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab.events.HostChangedEvent;
import mk.ukim.finki.emt.lab.events.HostCreatedEvent;
import mk.ukim.finki.emt.lab.events.HostDeletedEvent;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.projections.HostProjection;
import mk.ukim.finki.emt.lab.model.views.AccommodationsByHostView;
import mk.ukim.finki.emt.lab.repository.AccommodationsPerHostViewRepository;
import mk.ukim.finki.emt.lab.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService, AccommodationsPerHostViewRepository accommodationsPerHostViewRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostService = hostService;
        this.countryService = countryService;
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AccommodationsByHostView> findAllAccommodationsPerHost() {
        return accommodationsPerHostViewRepository.findAll();
    }

    @Override
    public AccommodationsByHostView findAccommodationsPerHost(Long id) {
        return accommodationsPerHostViewRepository.findByHostId(id);
    }

    @Override
    public void deleteById(Long id) {
        Host host = hostService.findById(id).orElseThrow();
        hostService.deleteById(id);
        applicationEventPublisher.publishEvent(new HostDeletedEvent(host));
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        Optional<Country> country = countryService.findById(host.country().getId());
        Host host1 = host.toHost(country.orElse(null));

        this.applicationEventPublisher.publishEvent(new HostCreatedEvent(host1));

        return hostService.save(host1).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        Country country = countryService.findById(host.country().getId()).orElseThrow();
        Host host1 = host.toHost(country);
        this.applicationEventPublisher.publishEvent(new HostChangedEvent(host1));

        return hostService.update(id, host1).map(DisplayHostDto::from);
    }

    @Override
    public List<HostProjection> getAllHostNames() {
        return hostService.getAllHostNames();
    }


}
