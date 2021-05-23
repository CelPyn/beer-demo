package com.axxes.demo.service;

import com.axxes.demo.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class DefaultBeerServiceTest {

    @Autowired
    private BeerService beerService;

    @Test
    void getAll() {
        final List<Beer> beers = beerService.getBeers();
        assertThat(beers).isNotNull().isNotEmpty().hasSize(7);
    }

    @Test
    void getOne() {
        final Optional<Beer> result = beerService.findById(1);
        assertThat(result).isNotNull().isNotEmpty();
        final Beer actual = result.get();
        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getName()).isEqualTo("Leffe");
    }

    @Test
    void getOne_notFound() {
        final Optional<Beer> result = beerService.findById(10);
        assertThat(result).isNotNull().isEmpty();
    }

    @Test
    void search() {
        final List<Beer> result = beerService.searchByName("West");
        assertThat(result).isNotNull().isNotEmpty().hasSize(2);
    }

    @Test
    void search_notFound() {
        final List<Beer> result = beerService.searchByName("Cristal");
        assertThat(result).isNotNull().isEmpty();
    }
}
