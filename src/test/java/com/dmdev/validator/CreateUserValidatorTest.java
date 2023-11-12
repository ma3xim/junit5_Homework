package com.dmdev.validator;

import com.dmdev.dto.CreateUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CreateUserValidatorTest {
    private CreateUserValidator createUserValidator;

    @BeforeEach
    void prepare(){
        createUserValidator = CreateUserValidator.getInstance();
    }

    @Test
    void dateShouldNotBeValid(){
        CreateUserDto user = CreateUserDto.builder()
                .name("Maxim")
                .birthday("11-10-1997")
                .email("max.bri@gamil.com")
                .password("123123")
                .role("USER")
                .gender("MALE")
                .build();

        var validationResult = createUserValidator.validate(user);
        assertThat(validationResult.getErrors().get(0).getCode()).isEqualTo("invalid.birthday");
    }

    @Test
    void genderShouldNotBeValid(){
        CreateUserDto user = CreateUserDto.builder()
                .name("Maxim")
                .birthday("1997-11-10")
                .email("max.bri@gamil.com")
                .password("123123")
                .role("USER")
                .gender("")
                .build();

        var validationResult = createUserValidator.validate(user);
        assertThat(validationResult.getErrors().get(0).getMessage()).isEqualTo("Gender is invalid");
    }

    @Test
    void roleShouldNotBeValid(){
        CreateUserDto user = CreateUserDto.builder()
                .name("Maxim")
                .birthday("1997-11-10")
                .email("max.bri@gamil.com")
                .password("123123")
                .role("")
                .gender("MALE")
                .build();

        var validationResult = createUserValidator.validate(user);
        assertThat(validationResult.getErrors().get(0).getCode()).isEqualTo("invalid.role");
    }

    @Test
    void validateShouldBeOkay(){
        CreateUserDto user = CreateUserDto.builder()
                .name("Maxim")
                .birthday("1997-11-10")
                .email("max.bri@gamil.com")
                .password("123123")
                .role("ADMIN")
                .gender("MALE")
                .build();

        var validationResult = createUserValidator.validate(user);
        assertThat(validationResult.getErrors()).isEmpty();
    }

    @Test
    void allNotValid(){
        CreateUserDto user = CreateUserDto.builder()
                .name("Maxim")
                .birthday("10-11-1997")
                .email("max.bri@gamil.com")
                .password("123123")
                .role("")
                .gender("")
                .build();

        var validationResult = createUserValidator.validate(user);
        assertThat(validationResult.getErrors()).hasSize(3);
    }


}