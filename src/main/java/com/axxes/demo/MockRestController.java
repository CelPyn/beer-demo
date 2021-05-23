package com.axxes.demo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.axxes.demo.domain.Beer;
import com.axxes.demo.domain.MinimalBeer;
import com.axxes.demo.service.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockRestController {

    private final BeerService beerService;

    public MockRestController(final BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/api/beer")
    public List<MinimalBeer> getAll() {
        return beerService.getBeers().stream().map(beer -> new MinimalBeer(beer.getId(), beer.getName())).collect(Collectors.toList());
    }

    @GetMapping("/api/beer/{id}")
    public ResponseEntity<Beer> getOne(@PathVariable("id") final int id) {
        final Optional<Beer> res = beerService.getBeers().stream().filter(beer -> beer.getId() == id).findFirst();

        return res.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
