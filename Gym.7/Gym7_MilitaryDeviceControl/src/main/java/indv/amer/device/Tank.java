package indv.amer.device;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class Tank implements Device {

    public void moveForward() {
        log.info("Tank is moving forward a step.");
    }
    public void moveBackward() {
        log.info("Tank is moving backward a step.");
    }
}
