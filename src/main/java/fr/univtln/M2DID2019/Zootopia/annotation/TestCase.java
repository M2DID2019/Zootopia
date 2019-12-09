package fr.univtln.M2DID2019.Zootopia.annotation;

import fr.univtln.M2DID2019.Zootopia.enumeration.CaseMode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TestCaseValidator.class)
public @interface TestCase {
    String message() default "{fr.univtln.M2DID2019.Zootopia.annotation.TestCaseValidator}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    CaseMode value();
}
