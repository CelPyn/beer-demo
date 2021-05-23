package com.axxes.demo.service;

import com.axxes.demo.domain.Beer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultBeerService implements BeerService {

    private final List<Beer> beers = List
                .of(new Beer(1, "Leffe",
                             "Leffe is a premium beer brand owned by InBev Belgium, " +
                                         "the European operating arm of the global Anheuser Busch InBev brewery giant. " +
                                         "There are several beers in the range, and they are marketed as Abbey beers. " +
                                         "They are brewed in large quantities and are widely distributed."),
                    new Beer(2, "Duvel", "Chief brewery for family-run, independent beer chain producing iconic Duvel beers."),
                    new Beer(3, "St. Bernardus", "St. Bernardus is a brewery in Watou, Belgium."),
                    new Beer(4, "Jupiler",
                             "Jupiler is a Belgian beer introduced in 1966, now brewed by Anheuser–Busch InBev at Piedboeuf Brewery " +
                                           "in the Jupille-sur-Meuse neighbourhood of Liège. Jupiler is the highest selling beer in Belgium, " +
                                           "with around 40 percent share by volume."),
                    new Beer(5, "Maes",
                             "Maes pils is a Belgian pils brewed by Alken-Maes. The beer was first produced in 1930 for Antwerp's Universal Exposition and called Prima Maezenbier. " +
                                        "After Jupiler and Stella Artois, it is the third best-selling pilsner in Belgium. In 2008, Alken-Maes was taken over by the Dutch Heineken Brewery."),
                    new Beer(6, "Westmalle Dubbel",
                             "Westmalle Dubbel (7%) is a dark red-brown beer with a rich, complex taste thanks to re-fermentation in the bottle."),
                    new Beer(7, "Westmalle Tripel",
                             "Westmalle Tripel (9.5%) is a clear Trappist beer, golden-yellow in colour, with amazingly fruity aromas of ripe banana and a lovely, subtle nose of hops."));

    @Override
    public List<Beer> getBeers() {
        return beers;
    }

    @Override
    public Optional<Beer> findById(final int id) {
        return beers.stream().filter(beer -> beer.getId() == id).findFirst();
    }

    @Override
    public List<Beer> searchByName(final String search) {
        return beers.stream().filter(beer -> beer.getName().startsWith(search)).collect(Collectors.toList());
    }
}
