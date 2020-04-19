package tr.kartal.airportticketapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "PASSENGER")
@Data
public class Passenger implements Serializable {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "flight_id_seq", sequenceName = "flight_id_seq",initialValue = 11, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_seq")
    private Integer id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAIL")
    @Email
    private String email;

}
