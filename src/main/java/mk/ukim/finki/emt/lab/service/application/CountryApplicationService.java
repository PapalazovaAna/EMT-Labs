package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.views.HostsByCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> findById(Long id);

    List<HostsByCountryView> findAllHostsPerCountry();

    HostsByCountryView findHostsPerCountry(Long id);
    void refreshMaterializedView();
}
