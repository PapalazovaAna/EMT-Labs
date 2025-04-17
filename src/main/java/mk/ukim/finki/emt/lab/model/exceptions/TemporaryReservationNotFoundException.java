package mk.ukim.finki.emt.lab.model.exceptions;

public class TemporaryReservationNotFoundException extends RuntimeException{
    public TemporaryReservationNotFoundException(Long id){
        super(String.format("Temporary reservation with id %d could not be found.", id));
    }
}
