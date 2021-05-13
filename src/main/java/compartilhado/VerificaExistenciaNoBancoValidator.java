package compartilhado;

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


    @Override
    public void initialize(VerificaExistenciaNoBanco constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        this.tabela = constraintAnnotation.tabela();
        this.campoASerPesquisado = constraintAnnotation.campo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Boolean valorJaExiste = entityManager.createQuery("select count(t) > 0 from "
                + tabela
                + " t where " + campoASerPesquisado + " :valor", Boolean.class)
                .setParameter("valor", value)
                .getSingleResult();
        return valorJaExiste;
    }

}
