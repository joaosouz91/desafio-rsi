package br.com.brasilprev.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostumerDTO implements AbstractDTO {

    private Long id;
    private String name;
    private List<CostumerAddressDTO> addressDTOList;
    private String phoneOne;
    private String phoneTwo;
    private Boolean status;

}
