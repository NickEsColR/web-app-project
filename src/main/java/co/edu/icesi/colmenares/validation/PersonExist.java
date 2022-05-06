package co.edu.icesi.colmenares.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PersonExistValidation.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonExist {
	String message() default "La persona debe existir";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
