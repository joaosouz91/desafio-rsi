package br.com.brasilprev.api.model;

import br.com.brasilprev.api.model.enumerator.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "customer_address")
public class CustomerAddress implements Model, Serializable {

    private static final long serialVersionUID = 5459075242343301188L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_customer", referencedColumnName="id", nullable=false)
    private Customer customer;

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
    @Column(name = "address_type")
    private AddressType addressType;

    @Override
    @JsonIgnore
    public String toString() {
        return new StringBuilder()
                .append(this.street)
                .append(", ")
                .append(this.number)
                .append("\nCEP")
                .append(this.cep)
                .append("\n")
                .append(this.district)
                .append(" - ")
                .append(this.city)
                .append("/")
                .append(this.state)
                .append("\n")
                .append(this.country)
                    .toString();
    }
}
