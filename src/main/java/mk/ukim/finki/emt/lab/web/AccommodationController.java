package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final HostApplicationService hostApplicationService;

    public AccommodationController(AccommodationService accommodationService, HostApplicationService hostApplicationService) {
        this.accommodationService = accommodationService;
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all accommodations", description = "Returns a list of all accommodations")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved accommodations")
    @GetMapping
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @Operation(summary = "Add new accommodation", description = "Creates and returns a new accommodation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accommodation created successfully"),
            @ApiResponse(responseCode = "404", description = "Host not found or invalid data")
    })
    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody Accommodation accommodation) {
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update accommodation", description = "Updates an existing accommodation with new data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accommodation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> update(
            @PathVariable Long id,
            @RequestBody Accommodation accommodation
    ) {
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete accommodation", description = "Deletes an accommodation by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accommodation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Rent accommodation", description = "Marks an accommodation as rented")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accommodation rented successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PostMapping("/rent/{id}")
    public ResponseEntity<Void> rent(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.setRented(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get statistics for accommodations", description = "Returns a map for all the accommodation categories and the number of available accommodations from that category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved statistics")
    @GetMapping("/statistics")
    public HashMap<Category, Integer> getStatistics(){
        return accommodationService.statisticsForCategories();
    }

    @GetMapping("/by-host")
    @Operation(summary = "List number of accommodations per host for every host")
    public ResponseEntity<?> findAllNumberOfAccommodationsPerHost() {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findAllAccommodationsPerHost());
    }

    @GetMapping("/by-host/{id}")
    @Operation(summary = "List number of accommodations per host for a given host")
    public ResponseEntity<?> findNumberOfAccommodationsPerHost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findAccommodationsPerHost(id));
    }
}