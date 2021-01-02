package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerResource.class)
@ActiveProfiles(value = {"teste"})
public class CostumerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService costumerService;

    @Test
    public void callingWithoutParameterShouldReturnOk() throws Exception {

        this.mockMvc.perform(get("/api/v1/customers")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnJustOneFromResult() throws Exception {


        this.mockMvc.perform(get("/api/v1/customers/{id}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
