package pl.training.bank.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = FundsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Funds {
    
    int maxLength() default 5;
    
    String message() default "Invalid value";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
