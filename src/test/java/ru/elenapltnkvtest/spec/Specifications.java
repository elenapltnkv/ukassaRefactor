package ru.elenapltnkvtest.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.elenapltnkvtest.test.BaseTest;


import java.util.UUID;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class Specifications extends BaseTest {


    public static RequestSpecification spec = with()
            .log().uri()
            .log().headers()
            .log().body()
            .baseUri(host)
            .header("Idempotence-Key", UUID.randomUUID())
            .contentType("application/json")
            .filter(new AllureRestAssured())
            .header("Authorization", "Basic " + BaseTest.encodedString);

    public static final ResponseSpecification resPect = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(URI)
            .log(BODY)
            .log(STATUS)
            .log(BODY)
            .log(ALL)
            .build();
}
