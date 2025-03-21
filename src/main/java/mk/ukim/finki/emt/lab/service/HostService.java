package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Host;

import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);
}
