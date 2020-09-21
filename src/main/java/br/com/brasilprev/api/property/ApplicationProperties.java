package br.com.brasilprev.api.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("desafiorsi")
public class ApplicationProperties {

    @Setter
    private String originAllowed = "http://localhost:8080";

    private final Security security = new Security();

    @Getter
    @Setter
    public static class Security {

        private boolean enableHttps;

    }

}
