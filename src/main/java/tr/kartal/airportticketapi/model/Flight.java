package tr.kartal.airportticketapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @SequenceGenerator(name = "flight_id_seq", sequenceName = "flight_id_seq", initialValue = 11, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_seq")
    private Integer id;

    @Column(name = "QUOTA")
    private Integer quota;

    @Column(name = "TICKETPRICE")
    private BigDecimal ticketPrice;

    @JsonIgnore
    @Column(name = "NUMBEROFSOLDTICKETS")
    private Integer numberOfSoldTickets = 0;

    @JsonIgnore
    @Column(name = "PRICEINCREASERATIO")
    private Integer priceIncreseRatio = 10;

    @JsonIgnore
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Column(name = "ROUTE_ID")
    private Integer routeId;

    @Column(name = "DEPARTUREDATE")
    private LocalDateTime departureDate;

    @Column(name = "ARRIVALDATE")
    private LocalDateTime arrivalDate;

    @JsonIgnore
    @JoinColumn(name = "AIRLINECOMPANY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private AirlineCompany airlineCompany;

    @Column(name = "AIRLINECOMPANY_ID")
    private Integer airlineCompanyId;

    @Override
    public String toString() {
        return "Flight{" + "price='" + ticketPrice + '\'' +
                ", route=" + route.toString() +
                ", departureTime=" + departureDate +
                ", arrivalTime=" + arrivalDate +
                ", airlineCompany=" + airlineCompany.toString() +
                '}';

    }

}
