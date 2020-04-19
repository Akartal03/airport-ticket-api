package tr.kartal.airportticketapi.exceptions;

public class AirlineCompanyNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = -3880429246973279969L;

    public AirlineCompanyNotFoundException(Integer airlineCompanyId) {
        super("AirlineCompany", "AirlineCompany-id", airlineCompanyId);
    }
}
