package ru.elenapltnkvtest.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;


import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class BaseTest {
    static protected Properties properties;
    static protected String host;
    static protected String username;
    protected static String token;
    static protected String originalInput;
    static protected String encodedString;


    @BeforeAll
    public static void beforeAll() throws IOException {

        properties = new Properties();
        properties.load(new FileInputStream("/home/user/IdeaProjects/ukassaRefactor/src/test/resources/application.properties "));
        host = (String) properties.get("host");
        token = (String) properties.get("token");
        username = (String) properties.get("username");
        originalInput = username + ":" + token;
        encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes(UTF_8));
        RestAssured.baseURI = host;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();


    }



    @BeforeEach
    void beforeEach() throws InterruptedException {
        Thread.sleep(5000);
        //wait(5000);

    }

}
