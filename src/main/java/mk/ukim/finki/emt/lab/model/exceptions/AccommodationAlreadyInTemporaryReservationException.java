package mk.ukim.finki.emt.lab.model.exceptions;

public class AccommodationAlreadyInTemporaryReservationException extends RuntimeException{
    public AccommodationAlreadyInTemporaryReservationException(Long id, String username){
        super(String.format("Accommodation with id %d is already in the temporary reservation for user %s", id, username));
    }
}
