package compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.String;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VerificaExistenciaNoBancoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerificaExistenciaNoBanco {

    String message() default "O valor informado já existe no Banco de dados";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

    Class<?> tabela();

    String campo();



}
