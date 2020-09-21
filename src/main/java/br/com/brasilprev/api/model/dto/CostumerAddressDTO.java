package br.com.brasilprev.api.model.dto;

import br.com.brasilprev.api.model.enumerator.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostumerAddressDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 8991551307090072017L;

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
