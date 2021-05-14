package compartilhado;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)

@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ

public @interface CpfOuCnpj {

    String message() default "O valor informado já existe no Banco de dados.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

}
