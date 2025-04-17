package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;

    public HostApplicationServiceImpl(HostService hostService) {
        this.hostService = hostService;
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }
}
