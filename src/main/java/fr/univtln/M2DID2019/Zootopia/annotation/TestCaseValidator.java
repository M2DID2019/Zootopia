package fr.univtln.M2DID2019.Zootopia.annotation;

import fr.univtln.M2DID2019.Zootopia.enumeration.CaseMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TestCaseValidator implements ConstraintValidator<TestCase, String> {
    private CaseMode caseMode;

    @Override
    public void initialize(TestCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return true;
        if (caseMode == CaseMode.UPPER) {
            return s.equals(s.toUpperCase());
        }
        else
            return s.equals(s.toLowerCase());
    }
}
