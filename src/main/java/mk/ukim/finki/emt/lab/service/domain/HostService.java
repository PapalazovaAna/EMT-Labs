package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.model.projections.HostProjection;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);

    List<Host> findAll();
    Optional<Host> save(Host host);
    Optional<Host> update(Long id, Host host);
    void deleteById(Long id);
    List<HostProjection> getAllHostNames();
}
