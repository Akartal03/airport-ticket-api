package tr.kartal.airportticketapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "ticket_id_seq", sequenceName = "ticket_id_seq", initialValue = 11, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq")
    private Integer id;

    @Column(name = "TICKETNUMBER", unique = true)
    private Integer ticketNumber;

    @JsonIgnore
    @JoinColumn(name = "PASSENGER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;

    @Column(name = "PASSENGER_ID")
    private Integer passengerId;

    @JsonIgnore
    @JoinColumn(name = "FLIGHT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;

    @Column(name = "FLIGHT_ID")
    private Integer flightId;

    @Column(name = "ISCANCEL")
    private Boolean isCancel = false;

    @Override
    public String toString() {
        return "TICKET {" +
                "ticketNumber=" + ticketNumber +
                ", passenger=" + passenger +
                ", flight=" + flight.toString() +
                '}';
    }
}
