package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.DesafioRsiApplication;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class) // NÃO É NECESSÁRIA: classe do junit 5 para execução dos testes. Notação @SpringBootTest já inclui a mesma
@AutoConfigureMockMvc // para uso do MockMvc
@SpringBootTest(classes = {DesafioRsiApplication.class}) // para subir os beans que os testes irão utilizar ao serem executados
@ActiveProfiles(value = {"teste"}) // especifica que será utilizada quando o profile teste estiver em vigor
public class CostumerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Flyway flyway;

    @Test
    public void callingWithoutParameterShouldReturnOk() throws Exception {

        this.mockMvc.perform(get("/api/v1/customers")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnJustOneFromResult() throws Exception {

        //linha não necessária, mas fica de exemplo para a possibilidade de uso quando a notação @SpringBootTest está especificada na classe
        flyway.migrate();

        this.mockMvc.perform(get("/api/v1/customers/{id}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
