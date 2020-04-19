package tr.kartal.airportticketapi.exceptions;

public class RouteNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = -3880429246973279969L;

    public RouteNotFoundException(Integer routeId) {
        super("Route", "Route-id", routeId);
    }
}
