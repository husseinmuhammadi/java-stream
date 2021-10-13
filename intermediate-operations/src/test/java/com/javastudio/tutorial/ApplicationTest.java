package com.javastudio.tutorial;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void stream_basics() {
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    // Calling the method stream() on a list of objects returns a regular object stream.
    @Test
    public void createStreamFromListOfObjects() {
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    // We use Stream.of() to create a stream from a bunch of object references.
    @Test
    public void createStreamFromBunchOfObjects() {
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);
    }

    @Test
    public void primitive_data_types() {
        IntStream.range(1, 4)
                .forEach(System.out::println);
    }

    // And primitive streams support the additional terminal aggregate operations sum() and average()
    @Test
    public void name() {
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(
                        avg -> Assertions.assertThat(avg).isEqualTo(5.0)
                );
    }
}