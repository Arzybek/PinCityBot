package kg.arzybek.bots.pincity;

import kg.arzybek.bots.pincity.config.BotProperties;
import kg.arzybek.bots.pincity.telegram.callbacks.CallbackHandler;
import org.aspectj.weaver.ast.Call;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PinCityBotApp {

    public static void main(String[] args) {
        SpringApplication.run(PinCityBotApp.class, args);
    }

}
