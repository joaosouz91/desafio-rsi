package br.com.brasilprev.api.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("desafiorsi")
public class DesafioRsiProperties {

    @Setter
    private String originAllowed = "http://localhost:8000";

    private final Seguranca seguranca = new Seguranca();

    @Getter
    @Setter
    public static class Seguranca {

        private boolean enableHttps;

    }

}
