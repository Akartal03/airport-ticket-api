package tr.kartal.airportticketapi.exceptions;

public class AirportNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = -3880429246973279969L;

    public AirportNotFoundException(Integer airportId) {
        super("Airport", "Airport-id", airportId);
    }
}
