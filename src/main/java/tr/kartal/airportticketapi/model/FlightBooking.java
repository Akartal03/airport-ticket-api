package tr.kartal.airportticketapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class FlightBooking {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "flightbooking_id_seq", sequenceName = "flightbooking_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flightbooking_id_seq")
    @Basic(optional = false)
    private Integer id;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "booking_flights", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private Set<Flight> flights;
}
