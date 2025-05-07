package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.repository.AccommodationsPerHostViewRepository;
import mk.ukim.finki.emt.lab.repository.HostsPerCountryViewRepository;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefresher {
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    public MaterializedViewRefresher(AccommodationsPerHostViewRepository accommodationsPerHostViewRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository) {
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
    }

    @PostConstruct
    public void init() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
        hostsPerCountryViewRepository.refreshMaterializedView();
    }
}