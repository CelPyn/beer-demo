package com.axxes.demo.domain;

public class MinimalBeer {

    private final int id;
    private final String name;

    public MinimalBeer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
