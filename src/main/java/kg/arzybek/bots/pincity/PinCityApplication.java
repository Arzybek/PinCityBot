package kg.arzybek.bots.pincity;

import kg.arzybek.bots.pincity.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class PinCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinCityApplication.class, args);
    }

}
