package com.axxes.demo.service;

import java.util.List;

import com.axxes.demo.domain.Beer;
import org.springframework.stereotype.Service;

@Service
public class DefaultBeerService implements BeerService {

    @Override
    public List<Beer> getBeers() {
        return List.of(new Beer(1, "Leffe",
                                "Leffe is a premium beer brand owned by InBev Belgium, " +
                                            "the European operating arm of the global Anheuser Busch InBev brewery giant. " +
                                            "There are several beers in the range, and they are marketed as Abbey beers. " +
                                            "They are brewed in large quantities and are widely distributed."),
                       new Beer(2, "Duvel", "Chief brewery for family-run, independent beer chain producing iconic Duval beers."),
                       new Beer(3, "St. Bernardus", "St. Bernardus is a brewery in Watou, Belgium."),
                       new Beer(4, "Jupiler",
                                "Jupiler is a Belgian beer introduced in 1966, now brewed by Anheuser–Busch InBev at Piedboeuf Brewery " +
                                              "in the Jupille-sur-Meuse neighbourhood of Liège. Jupiler is the highest selling beer in Belgium, " +
                                              "with around 40 percent share by volume."),
                       new Beer(5, "Maes",
                                "Maes pils is a Belgian pils brewed by Alken-Maes. The beer was first produced in 1930 for Antwerp's Universal Exposition and called Prima Maezenbier. " +
                                           "After Jupiler and Stella Artois, it is the third best-selling pilsner in Belgium. In 2008, Alken-Maes was taken over by the Dutch Heineken Brewery."));
    }
}
