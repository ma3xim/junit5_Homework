package com.dmdev.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidationResultTest {
    private ValidationResult validationResult;
    //private static final List<Error> errors = new ArrayList<>();


    @BeforeEach
    void prepare(){
        validationResult = new ValidationResult();
    }

    @Test
    void errorsShouldBeEmpty(){
        assertThat(validationResult.isValid()).isTrue();
    }

    @Test
    void errorsShouldNotBeEmpty(){
        validationResult.add(Error.of("code", "message"));
        assertThat(validationResult.getErrors()).hasSize(1);
    }

}