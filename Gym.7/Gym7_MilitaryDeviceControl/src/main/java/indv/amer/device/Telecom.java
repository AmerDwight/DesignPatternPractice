package indv.amer.device;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Telecom implements Device {
    private boolean isConnected = false;

    public void connect() {
        if(isConnected){
            log.info("Telecom is already connected.");
        }else{
            isConnected = true;
            log.info("Telecom is connected.");
        }
    }
    public void disconnect() {
        if(!isConnected){
            log.info("Telecom is already disconnected.");
        }else{
            isConnected = false;
            log.info("Telecom is disconnected.");
        }
    }
}
