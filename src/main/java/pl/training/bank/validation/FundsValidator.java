package pl.training.bank.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FundsValidator implements ConstraintValidator<Funds, Long> {

    private String message;
    
    @Override
    public void initialize(Funds fundsAnnotation) {
        message = fundsAnnotation.message();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext cvc) {
        boolean result;
        try {
            result = value.toString().matches("\\d{2,}");
        } catch (RuntimeException ex) {
            result = false;
        }
        return result;
    }
    
}
