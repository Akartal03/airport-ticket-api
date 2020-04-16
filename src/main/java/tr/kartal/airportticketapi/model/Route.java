package tr.kartal.airportticketapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "ROUTE")
@Data
public class Route implements Serializable {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "route_id_seq", sequenceName = "route_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_id_seq")
    @Basic(optional = false)
    private Integer id;

    @JoinColumn(name = "FROM_AIRPORT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport from;

    @Column(name = "FROM_AIRPORT_ID")
    private Integer fromAirportId;

    @JoinColumn(name = "TO_AIRPORT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport to;

    @Column(name = "TO_AIRPORT_ID")
    private Integer toAirportId;


}
