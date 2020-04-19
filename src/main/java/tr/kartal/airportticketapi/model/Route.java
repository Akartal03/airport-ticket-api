package tr.kartal.airportticketapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ROUTE")
@Data
public class Route implements Serializable {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "route_id_seq", sequenceName = "route_id_seq", initialValue = 11, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_id_seq")
    private Integer id;

    @JsonIgnore
    @JoinColumn(name = "FROM_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport from;

    @Column(name = "FROM_ID")
    private Integer fromId;

    @JsonIgnore
    @JoinColumn(name = "TO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport to;

    @Column(name = "TO_ID")
    private Integer toId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(from, route.from) &&
                Objects.equals(to, route.to);
    }

    @Override
    public String toString() {
        return "(" + from +
                "->" + to +
                ')';
    }

}
