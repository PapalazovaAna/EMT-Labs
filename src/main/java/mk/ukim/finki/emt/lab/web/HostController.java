package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.CreateHostDto;
import mk.ukim.finki.emt.lab.model.projections.HostProjection;
import mk.ukim.finki.emt.lab.service.application.CountryApplicationService;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts", description = "")
public class HostController {
    private final HostApplicationService hostApplicationService;
    private final CountryApplicationService countryApplicationService;

    public HostController(HostApplicationService hostApplicationService, CountryApplicationService countryApplicationService) {
        this.hostApplicationService = hostApplicationService;
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all hosts")
    public ResponseEntity<?> listAllHosts() {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific host")
    public ResponseEntity<?> listHost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new host", description = "Adds a host with details provided in the request body")
    public ResponseEntity<?> addHost(@RequestBody CreateHostDto hostDto) {
        return ResponseEntity.ok(hostApplicationService.save(hostDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit a host", description = "Edits an existing host's details")
    public ResponseEntity<?> editHost(@RequestBody CreateHostDto hostDto, @PathVariable Long id) {
        return ResponseEntity.ok(hostApplicationService.update(id, hostDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a host", description = "Deletes a host by its ID")
    public ResponseEntity<?> deleteHost(@PathVariable Long id) {
        hostApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/per-country")
    @Operation(summary = "")
    public ResponseEntity<?> findAllNumberOfHostsPerCountry() {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAllHostsPerCountry());
    }

    @GetMapping("/per-country/{id}")
    @Operation(summary = "")
    public ResponseEntity<?> findNumberOfHostsPerCountry(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findHostsPerCountry(id));
    }

    @GetMapping("/names")
    public ResponseEntity<List<HostProjection>> getHostNames() {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.getAllHostNames());
    }
}
