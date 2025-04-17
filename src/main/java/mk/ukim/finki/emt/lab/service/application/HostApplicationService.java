package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> findById(Long id);

}
