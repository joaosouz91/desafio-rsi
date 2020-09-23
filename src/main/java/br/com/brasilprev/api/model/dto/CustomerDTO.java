package br.com.brasilprev.api.model.dto;

import br.com.brasilprev.api.model.enumerator.ModelStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "id", "name", "addressList", "phoneOne", "phoneTwo", "enabled"
})
public class CustomerDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 6376627089798654847L;

    private Long id;

    @NotNull
    private String name;

    @Valid
    @NotNull
    private List<CustomerAddressDTO> addressList;

    @NotNull
    private String phoneOne;

    private String phoneTwo;

    @NotNull
    private ModelStatus status;

}
