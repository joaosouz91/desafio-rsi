package br.com.brasilprev.api.dto;

import br.com.brasilprev.api.model.enumerator.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostumerAddressDTO implements AbstractDTO {

    private Long id;
    private String street;
    private String number;
    private String cep;
    private String district;
    private String city;
    private String state;
    private String country;
    private AddressType addressType;

}
