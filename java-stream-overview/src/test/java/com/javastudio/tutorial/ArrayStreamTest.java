package com.javastudio.tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class ArrayStreamTest {

    public final Logger logger = LoggerFactory.getLogger(ArrayStreamTest.class);

    @Test
    void givenArrayAsSourceOfStream_whenCreateTheStream_thenItShouldBeCreated() {
        Stream<String> countries = Stream.of("Germany", "Netherlands", "Belgium");
        Assertions.assertEquals(countries.count(), 3);
    }
}
