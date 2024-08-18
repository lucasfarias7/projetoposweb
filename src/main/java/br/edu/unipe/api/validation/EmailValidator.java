package br.edu.unipe.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator
        implements ConstraintValidator<EmailValidation, String> {

    private String message;

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String campo,
                           ConstraintValidatorContext constraintValidatorContext) {
        if(campo.contains("@unipe.edu.br")){
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message + campo)
                .addConstraintViolation();

        return false;
    }
}
