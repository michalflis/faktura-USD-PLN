package faktura.USD.PLN.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "faktura.USD.PLN")
public class JpaConfiguration {
}