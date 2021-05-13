package br.com.zupacademy.renatomendes.cdc.autor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank @Length(min = 3) String nome;

    @NotNull
    @Column (unique = true, nullable = false)
    private @NotBlank @Email String email;

    private @NotBlank @Length(min = 10, max = 400) String descricao;
    @CreationTimestamp
    private LocalDateTime criadoEm = LocalDateTime.now(ZoneOffset.UTC);

    @Deprecated
    public Autor(){};

    public Autor(String nome, String email, String descricao) {
        Assert.hasLength(nome, "Nome é obrigatório");
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Autor -- {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCriadoEm() {
        return this.criadoEm;
    }

    public Long getId()  {
        return id;
    }
}
