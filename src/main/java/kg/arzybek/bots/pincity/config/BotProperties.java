package kg.arzybek.bots.pincity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "bot")
@Data
@PropertySource("classpath:application.yml")
public class BotProperties {

    String name;

    String token;

}
