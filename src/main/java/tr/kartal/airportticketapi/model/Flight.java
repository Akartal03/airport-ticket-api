package tr.kartal.airportticketapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "FLIGHT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight implements Serializable {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "flight_id_seq", sequenceName = "flight_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_seq")
    @Basic(optional = false)
    private Long id;

    @Column(name = "DEPARTURE")
    private String departure;

    @Column(name = "ARRIVAL")
    private String arrival;

    @Column(name = "DEPARTUREDATE")
    private LocalDateTime departureDate;

    @Column(name = "ARRIVALDATE")
    private LocalDateTime arrivalDate;

    @ManyToMany(mappedBy = "flights", fetch = FetchType.LAZY)
    private Set<FlightBooking> bookings;

}
