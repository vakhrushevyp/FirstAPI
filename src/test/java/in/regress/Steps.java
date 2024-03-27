package in.regress;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Steps {

    @Step
    public static String goWebTours(){

        given()
                .when()
                .get("http://localhost:1080/WebTours/")
                .then()
                .statusCode(200);

        Response response = (Response) given()
                .when()
                .get("http://localhost:1080/cgi-bin/welcome.pl?signOff=true")
                .then()
                .statusCode(200);
        System.out.println(response);


        given()

                .when()
                .get("http://localhost:1080/cgi-bin/nav.pl?in=home")
                .then()
                .statusCode(200);










        return "";
    }

}
