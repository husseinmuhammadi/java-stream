package com.javastudio.tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperationsTest {

    private final Logger logger = Mockito.mock(Logger.class);
    private Stream<String> countries;
    private List<String> countryCollection;

    @BeforeEach
    void setUp() {
        // countries = Stream.of("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
        countryCollection = Arrays.asList("Germany", "Netherlands", "Belgium");
        countries = countryCollection.stream();
        // countries = Stream.of("Germany", "Netherlands", "Belgium");
    }

    @Test
    void quiz1_whatWillWrittenWhileMappingStream() {
        countries.map(c -> {
            System.out.println(c);
            return c.toLowerCase();
        });
    }

    @Test
    void quiz2_whatWillWrittenWhileFilteringStream() {
        countries.filter(c -> {
            System.out.println(c);
            return c.startsWith("B");
        });
    }

    @Test
    void quiz3_whatWillWrittenWhileRunningAPipelineOfStreams() {
        countries.filter(c -> {
            System.out.println(c);
            return c.startsWith("B");
        }).map(c -> {
            System.out.println(c);
            return c.toLowerCase();
        });
    }

    @Test
    void quiz4_whatWillWrittenWhileRunningTheCodeBelow() {
        countries.filter(c -> {
            System.out.println("filter " + c);
            return c.startsWith("B");
        }).map(c -> {
            System.out.println("map " + c);
            return c.toLowerCase();
        }).collect(Collectors.toList());
    }

    @Test
    void givenIntermediateOperations_whenPipelineFilterWithMap_thenItShouldNotRunAnyCodeInside() {
        countries.map(country -> {
            logger.info(country);
            return country.charAt(0);
        }).filter(initial -> {
            logger.info("{}", initial);
            return initial == 'B';
        });

        Mockito.verify(logger, Mockito.never())
                .info(ArgumentMatchers.anyString());
    }

    @Test
    void givenTerminalOperation_whenUsingWithIntermediateOperations_thenItWillRunTheCode() {
        countries.filter(c -> c.startsWith("B")).map(c -> {
            logger.info(c.toLowerCase());
            return c.toLowerCase();
        }).collect(Collectors.toList());

        Mockito.verify(logger, Mockito.atLeast(1))
                .info(ArgumentMatchers.eq("belgium"));

        Mockito.verify(logger, Mockito.atMost(1))
                .info(ArgumentMatchers.eq("belgium"));
    }
}
