package br.com.brasilprev.api.model.dto;

import br.com.brasilprev.api.model.enumerator.AddressType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "id", "street", "number", "cep", "district", "city", "state", "addressType"
})
public class CustomerAddressDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 8991551307090072017L;

    private Long id;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String cep;

    @NotNull
    private String district;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    private AddressType addressType;

}
