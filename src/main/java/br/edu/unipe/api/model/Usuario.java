package br.edu.unipe.api.model;

import br.edu.unipe.api.validation.EmailValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "O E-mail esta zoado")
    @Column(name = "email")
    @EmailValidation
    private String email;

    private String nome;

    @ManyToOne
    private Endereco endereco;

}
