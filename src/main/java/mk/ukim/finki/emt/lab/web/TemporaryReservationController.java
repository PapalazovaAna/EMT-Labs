package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.lab.dto.TemporaryReservationDto;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.service.application.TemporaryReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/temporary-reservation")
@Tag(name = "Temporary Reservation API", description = "Endpoints for managing the temporary reservation")
public class TemporaryReservationController {
    private final TemporaryReservationApplicationService temporaryReservationApplicationService;

    public TemporaryReservationController(TemporaryReservationApplicationService temporaryReservationApplicationService) {
        this.temporaryReservationApplicationService = temporaryReservationApplicationService;
    }

    @Operation(
            summary = "Get active temporary reservation",
            description = "Retrieves the active temporary reservation for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Temporary reservation retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Temporary reservation not found")}
    )
    @GetMapping
    public ResponseEntity<TemporaryReservationDto> getActiveTemporaryReservation(HttpServletRequest request){
        String username = request.getRemoteUser();
        return temporaryReservationApplicationService.getActiveTemporaryReservation(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add accommodation to temporary reservation",
            description = "Adds an accommodation to the temporary reservation for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Accommodation added to temporary reservation successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Accommodation not found")}
    )
    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<TemporaryReservationDto> addAccommodationToTemporaryReservation(@PathVariable Long id, Authentication authentication){
        try{
            User user = (User) authentication.getPrincipal();
            return temporaryReservationApplicationService.addAccommodationToTemporaryReservation(user.getUsername(), id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }catch (RuntimeException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Confirm reservations",
            description = "Confirms all temporary reservations for the currently logged-in user. All accommodations will be marked as rented."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservations confirmed successfully"),
            @ApiResponse(responseCode = "400", description = "One or more accommodations are no longer available")
    })
    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmReservations(@RequestParam Long userId) {
        try {
            temporaryReservationApplicationService.confirmReservations(userId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
