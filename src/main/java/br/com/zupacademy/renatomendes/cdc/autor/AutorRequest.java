package br.com.zupacademy.renatomendes.cdc.autor;

import compartilhado.VerificaExistenciaNoBanco;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutorRequest {
    private @NotBlank @Length(min = 3) String nome;
//    @VerificaExistenciaNoBanco(tabela = Autor.class, campo = "email")
    private @NotBlank @Email String email;
    private @NotBlank @Length(min = 10, max = 400) String descricao;


    public AutorRequest(@NotBlank @Length(min = 3) String nome
            , @NotBlank @Email String email
            , @NotBlank @Length(min = 10, max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "AutorRequest{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return email;
    }
}
