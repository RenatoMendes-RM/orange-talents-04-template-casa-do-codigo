package compartilhado;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.lang.reflect.Field;
import java.util.function.Function;

@Component
public class CampoUnicoComSpring<T, P> implements Validator {
    private String campo;
    private Class<? extends T> classeParaSerValidada;
    private Function<P, Boolean> metodoQueVaiNoBanco;

    public CampoUnicoComSpring(String campo,
                               Class<? extends T> classeParaSerValidada,
                               Function<P, Boolean> metodoQueVaiNoBanco) {
        this.campo = campo;
        this.classeParaSerValidada = classeParaSerValidada;
        this.metodoQueVaiNoBanco = metodoQueVaiNoBanco;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return this.classeParaSerValidada.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            Field declaredField = classeParaSerValidada.getDeclaredField(this.campo);
            declaredField.setAccessible(true);
            Object valorASerPesquisado = declaredField.get(target);
            Boolean existeNoBanco = metodoQueVaiNoBanco.apply((P) valorASerPesquisado);

            if (existeNoBanco) {
                errors.rejectValue(campo
                        , "campoUnico"
                        , "O campo deve ser unico na tabela do banco");
            }

        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}