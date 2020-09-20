package br.com.brasilprev.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostumerDTO {

    private Long id;
    private String name;
    private List<CostumerAddressDTO> addressList;
    private String phoneOne;
    private String phoneTwo;
    private boolean enabled;

}
