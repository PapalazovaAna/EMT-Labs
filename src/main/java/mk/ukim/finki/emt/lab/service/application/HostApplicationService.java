package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.CreateHostDto;
import mk.ukim.finki.emt.lab.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.projections.HostProjection;
import mk.ukim.finki.emt.lab.model.views.AccommodationsByHostView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> findById(Long id);
    void refreshMaterializedView();

    List<AccommodationsByHostView> findAllAccommodationsPerHost();

    AccommodationsByHostView findAccommodationsPerHost(Long id);

    List<DisplayHostDto> findAll();
    void deleteById(Long id);
    Optional<DisplayHostDto> save(CreateHostDto host);
    Optional<DisplayHostDto> update(Long id, CreateHostDto host);
    List<HostProjection> getAllHostNames();
}
