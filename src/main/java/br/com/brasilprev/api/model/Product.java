package br.com.brasilprev.api.model;

import br.com.brasilprev.api.model.enumerator.ModelStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product implements Model, Serializable {

    private static final long serialVersionUID = -2886031201978035609L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    private String description;

    @NotNull
    private String sku;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    private ModelStatus status;
}
