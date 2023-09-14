package kg.arzybek.bots.pincity;

import kg.arzybek.bots.pincity.config.BotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class PinCityBotApp {

    public static void main(String[] args) {
        SpringApplication.run(PinCityBotApp.class, args);
    }

}
