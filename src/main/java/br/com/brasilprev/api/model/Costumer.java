package br.com.brasilprev.api.model;

import br.com.brasilprev.api.dto.CostumerDTO;
import br.com.brasilprev.api.model.enumerator.ModelStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "costumer")
public class Costumer implements Model, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 60)
    private String name;

    @OneToMany(mappedBy = "idCostumer", fetch = FetchType.LAZY)
    private List<CostumerAddress> adressList;

    @NotNull
    private String phoneOne;

    private String phoneTwo;

    private ModelStatus status;

}
