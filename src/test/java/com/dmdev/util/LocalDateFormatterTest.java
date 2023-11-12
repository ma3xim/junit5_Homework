package com.dmdev.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class LocalDateFormatterTest {

    @Test
    void formatAndDateIsValid(){
        String date = "1997-11-10";
        var actualResult = LocalDateFormatter.format(date);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertThat(actualResult).isEqualTo(localDate);
    }

    @Test
    void formatAndDateIsNotValid(){
        String date = "10-11-1997";

        assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format(date));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForValidation")
    void isValidTest(String date, boolean expected){
        boolean actual = LocalDateFormatter.isValid(date);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> getArgumentsForValidation(){
        return Stream.of(
                Arguments.of("1997-11-10", true),
                Arguments.of("10-11-1997", false),
                Arguments.of("1997-11-10 22:15", false),
                Arguments.of(null, false)
        );
    }
}