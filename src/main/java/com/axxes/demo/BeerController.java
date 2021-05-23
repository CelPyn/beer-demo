package com.axxes.demo;

import com.axxes.demo.domain.Beer;
import com.axxes.demo.domain.MinimalBeer;
import com.axxes.demo.service.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(final BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/api/beer")
    public List<MinimalBeer> getAll() {
        return beerService.getBeers().stream().map(this::maptoMinimal).collect(Collectors.toList());
    }

    @GetMapping("/api/beer/{id}")
    public ResponseEntity<Beer> getOne(@PathVariable("id") final int id) {
        final Optional<Beer> res = beerService.findById(id);
        return res.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/beer/search")
    public ResponseEntity<List<MinimalBeer>> search(@RequestParam("q") final String query) {
        final List<MinimalBeer> searchResult = beerService.searchByName(query).stream().map(this::maptoMinimal).collect(Collectors.toList());

        if (searchResult.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(searchResult);
    }

    private MinimalBeer maptoMinimal(final Beer beer) {
        return new MinimalBeer(beer.getId(), beer.getName());
    }
}
