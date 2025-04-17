package mk.ukim.finki.emt.lab.model.exceptions;

public class AccommodationNotFoundException extends RuntimeException{
    public AccommodationNotFoundException(Long id){
        super(String.format("Accommodation with id %d could not be found.", id));
    }
}
