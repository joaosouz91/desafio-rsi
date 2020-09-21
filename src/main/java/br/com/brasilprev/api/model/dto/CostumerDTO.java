package br.com.brasilprev.api.model.dto;

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
public class CostumerDTO implements AbstractDTO, Serializable {

    private static final long serialVersionUID = 6376627089798654847L;

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Valid
    private List<CostumerAddressDTO> addressList;

    @NotNull
    private String phoneOne;

    private String phoneTwo;

    @NotNull
    private boolean enabled;

}
