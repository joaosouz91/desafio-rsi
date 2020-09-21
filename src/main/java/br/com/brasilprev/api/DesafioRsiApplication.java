package br.com.brasilprev.api;

import br.com.brasilprev.api.property.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class DesafioRsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioRsiApplication.class, args);
	}

}
