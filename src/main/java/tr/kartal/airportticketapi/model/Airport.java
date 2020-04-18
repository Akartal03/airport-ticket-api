package tr.kartal.airportticketapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "AIRPORT")
@Data
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "airport_id_seq", sequenceName = "airport_id_seq",initialValue = 11, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airport_id_seq")
    private Integer id;

    @NotNull
    @Column(name = "IATACODE", unique = true)
    private String iataCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRYISOCODE")
    private String countryIsoCode;

    @Override
    public String toString(){
        return "Airport: [" +iataCode + ", " + name + ", "+ countryIsoCode + "]";
    }
}
