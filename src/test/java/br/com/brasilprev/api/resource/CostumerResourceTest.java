package br.com.brasilprev.api.resource;

import br.com.brasilprev.api.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerResource.class)
@ActiveProfiles(value = {"dev"})
public class CostumerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService costumerService;

    @Test
    public void callingWithoutParameterShouldReturnBadRequest() throws Exception {

        this.mockMvc.perform(get("/api/v1/customers")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
