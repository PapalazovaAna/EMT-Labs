package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.dto.GuestDto;
import mk.ukim.finki.emt.lab.model.domain.Guest;
import mk.ukim.finki.emt.lab.service.domain.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest API", description = "Endpoints for managing guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @Operation(summary = "Get all guests", description = "Returns a list of all registered guests")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved guests")
    @GetMapping
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @Operation(summary = "Add a new guest", description = "Creates and returns a new guest")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Guest added successfully"),
            @ApiResponse(responseCode = "404", description = "Invalid data or associated entities not found")
    })
    @PostMapping("/add")
    public ResponseEntity<Guest> save(@RequestBody GuestDto guest) {
        return guestService.save(guest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}