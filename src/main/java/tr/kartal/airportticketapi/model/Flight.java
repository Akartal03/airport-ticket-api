package tr.kartal.airportticketapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "FLIGHT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight implements Serializable {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "flight_id_seq", sequenceName = "flight_id_seq",initialValue = 11, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_seq")
    private Integer id;

    @Column(name = "QUOTA")
    private Integer quota;

    @Column(name = "TICKETPRICE")
    private BigDecimal ticketPrice;

    @Column(name = "NUMBEROFSOLDTICKETS")
    private Integer numberOfSoldTickets = 0;

    @Column(name = "PRICEINCREASERATIO")
    private Integer priceIncreseRatio = 0;

    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Route route;

    @Column(name = "DEPARTUREDATE")
    private LocalDateTime departureDate;

    @Column(name = "ARRIVALDATE")
    private LocalDateTime arrivalDate;

    @JoinColumn(name = "AIRLINECOMPANY_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private AirlineCompany airlineCompany;

    @Override
    public String toString() {
        return "Flight{" + "price='" + ticketPrice + '\'' +
                ", route=" + route.toString() +
                ", departureTime=" + departureDate +
                ", arrivalTime=" + arrivalDate +
                ", airlineCompany=" + airlineCompany.getName() +
                '}';

    }

}
