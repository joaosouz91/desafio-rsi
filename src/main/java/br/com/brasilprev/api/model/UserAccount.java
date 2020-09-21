package br.com.brasilprev.api.model;

import br.com.brasilprev.api.model.enumerator.ModelStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount implements Model, Serializable {

    private static final long serialVersionUID = 7234494484529979154L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private ModelStatus status;

}
