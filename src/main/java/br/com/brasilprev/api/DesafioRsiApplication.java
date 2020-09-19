package br.com.brasilprev.api;

import br.com.brasilprev.api.property.DesafioRsiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DesafioRsiProperties.class)
public class DesafioRsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioRsiApplication.class, args);
	}

}
