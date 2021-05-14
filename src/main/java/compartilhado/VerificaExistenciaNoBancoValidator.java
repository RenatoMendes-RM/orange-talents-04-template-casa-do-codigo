package compartilhado;

import br.com.zupacademy.renatomendes.cdc.autor.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class VerificaExistenciaNoBancoValidator
        implements ConstraintValidator <VerificaExistenciaNoBanco, Object> {

    private String campoASerPesquisado;
    private Class<?> tabela;
    @PersistenceContext private EntityManager entityManager;

    //@Autowired private AutorRepository autorRepository;

    @Override
    public void initialize(VerificaExistenciaNoBanco constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        this.tabela = constraintAnnotation.tabela();
        this.campoASerPesquisado = constraintAnnotation.campo();
    }

    // check-out 2021-04-29 Alefh
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Boolean valorJaExiste = entityManager.createQuery("select count(t) > 0 from "
                + tabela.getName()
                + " t where " + campoASerPesquisado + " = :valor", Boolean.class)
                .setParameter("valor", value)
                .getSingleResult();
        return valorJaExiste;
    }

}
