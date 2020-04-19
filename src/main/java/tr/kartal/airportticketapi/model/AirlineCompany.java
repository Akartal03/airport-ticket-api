package tr.kartal.airportticketapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "AIRLINECOMPANY")
public class AirlineCompany {

    @Id
    @SequenceGenerator(name = "airlinecompany_id_seq", sequenceName = "airlinecompany_id_seq",initialValue = 5, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airlinecompany_id_seq")
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;

}
