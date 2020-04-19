package tr.kartal.airportticketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class AirportticketapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportticketapiApplication.class, args);
    }

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));
    }

}
