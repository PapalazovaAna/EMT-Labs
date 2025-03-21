package mk.ukim.finki.emt.lab.controller;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDto;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodation){
        return accommodationService.save(accommodation).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> update(
            @PathVariable Long id,
            @RequestBody AccommodationDto accommodation
    ) {
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<Void> rent(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.setRented(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
