package uz.sardor.passportQueueSystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PassportQueueSystemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassportQueueSystemsApplication.class, args);
    }

}
