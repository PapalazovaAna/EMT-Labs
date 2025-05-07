package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.projections.HostProjection;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> save(Host host) {
        if(host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()){
            return Optional.of(hostRepository.save(new Host(host.getName(), host.getSurname(), host.getCountry())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id)
                .map(existingHost -> {
                    if (host.getName() != null) {
                        existingHost.setName(host.getName());
                    }
                    if (host.getSurname() != null) {
                        existingHost.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
                        existingHost.setCountry(countryService.findById(host.getCountry().getId()).get());
                    }

                    return hostRepository.save(existingHost);
                });
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public List<HostProjection> getAllHostNames() {
        return hostRepository.takeNameAndSurnameByProjection();
    }
}
