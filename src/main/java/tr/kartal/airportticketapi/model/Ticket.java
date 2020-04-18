package tr.kartal.airportticketapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String ticketNumber;

    @JoinColumn(name = "PASSENGER_ID", referencedColumnName = "ID")
    @OneToOne
    @NotNull
    private Passenger passenger;

    @JoinColumn(name = "FLIGHT_ID", referencedColumnName = "ID")
    @OneToOne
    @NotNull
    private Flight flight;

    @JoinColumn(name = "ISCANCEL")
    @NotNull
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
