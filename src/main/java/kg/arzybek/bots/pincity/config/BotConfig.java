package kg.arzybek.bots.pincity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@Data
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "bot")
@Component
public class BotConfig {

    String botName;

    String token;

}
