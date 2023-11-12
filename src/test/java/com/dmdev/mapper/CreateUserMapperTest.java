package com.dmdev.mapper;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateUserMapperTest {

    private CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    @Test
    void mapTest(){
        CreateUserDto dto = CreateUserDto.builder()
                .name("Maxim")
                .birthday("1997-11-10")
                .email("max.bri@gamil.com")
                .password("123123")
                .role(Role.ADMIN.name())
                .gender(Gender.MALE.name())
                .build();

        User result = createUserMapper.map(dto);

        User expected = User.builder()
                .name("Maxim")
                .gender(Gender.MALE)
                .role(Role.ADMIN)
                .email("max.bri@gamil.com")
                .password("123123")
                .birthday(LocalDate.of(1997, 11, 10))
                .build();

        assertThat(result).isEqualTo(expected);
    }
}