package com.axxes.demo;

import com.axxes.demo.domain.Beer;
import com.axxes.demo.domain.MinimalBeer;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BeerControllerIT {

    @Test
    void getAll() {
        final Response response = given().get("http://localhost:8080/api/beer").then().statusCode(200).extract().response();
        final List<Object> stringBody = response.jsonPath().getList("");
        assertThat(stringBody).isNotNull().isNotEmpty().hasSize(7);
    }

    @Test
    void getOne() {
        final Response response = given().get("http://localhost:8080/api/beer/1").then().statusCode(200).extract().response();
        final Map<String, String> stringBody = response.jsonPath().get("");
        assertThat(stringBody).isNotNull().isNotEmpty().hasSize(3);

        final Beer result = response.as(Beer.class);
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void getOne_notFound() {
        given().get("http://localhost:8080/api/beer/14").then().statusCode(404);
    }

    @Test
    void search() {
        final Response response =
                    given().queryParam("q", "West").get("http://localhost:8080/api/beer/search").then().statusCode(200).extract().response();
        final List<Object> stringBody = response.jsonPath().getList("");
        assertThat(stringBody).isNotNull().isNotEmpty().hasSize(2);

        final MinimalBeer[] result = response.as(MinimalBeer[].class);
        assertThat(result).hasSize(2);
        assertThat(result[0].getId()).isEqualTo(6);
    }

    @Test
    void search_notFound() {
        given().queryParam("q", "Cristal").get("http://localhost:8080/api/beer/search").then().statusCode(204);
    }
}
