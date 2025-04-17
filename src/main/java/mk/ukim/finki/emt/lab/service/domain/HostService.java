package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);
}
