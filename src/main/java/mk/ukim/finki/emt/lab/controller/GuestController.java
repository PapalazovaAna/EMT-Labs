package mk.ukim.finki.emt.lab.controller;

import mk.ukim.finki.emt.lab.dto.GuestDto;
import mk.ukim.finki.emt.lab.model.Guest;
import mk.ukim.finki.emt.lab.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Guest> save(@RequestBody GuestDto guest){
        return guestService.save(guest).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
