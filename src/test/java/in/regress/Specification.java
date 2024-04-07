package in.regress;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost/")
                .setPort(1080)
                .setContentType("application/x-www-form-urlencoded")
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void installSpec(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }

    public static void installSpec(ResponseSpecification responseSpecification) {
        RestAssured.responseSpecification = responseSpecification;
    }

    public static void installSpec(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }












}
