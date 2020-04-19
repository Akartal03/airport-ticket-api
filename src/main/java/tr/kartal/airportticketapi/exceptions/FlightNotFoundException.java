package tr.kartal.airportticketapi.exceptions;

public class FlightNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = -3880429246973279969L;

    public FlightNotFoundException(Integer flightId) {
        super("Flight", "Flight-id", flightId);
    }
}
