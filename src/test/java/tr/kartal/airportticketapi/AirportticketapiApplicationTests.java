package tr.kartal.airportticketapi;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@Log4j2
class AirportticketapiApplicationTests {

    @Test
    void contextLoads() {
        BigDecimal sum = new BigDecimal(10);
        for (int i = 0; i < 9; i++) {
            sum = sum.add(sum.divide(new BigDecimal(10), 2, RoundingMode.HALF_UP));
        }
        log.info(sum);
    }

}
