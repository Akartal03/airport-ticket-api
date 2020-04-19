package tr.kartal.airportticketapi.exceptions;

public class PassengerNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = -3880429246973279969L;

    public PassengerNotFoundException(Integer passengerId) {
        super("Passenger", "Passenger-id", passengerId);
    }
}
