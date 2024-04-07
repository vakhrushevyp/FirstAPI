package in.regress;

import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class Steps {

    @Step
    public static String goWebToursDefaultSpec(){
        given()
                .when()
                .get("/WebTours/")
                .then();
        Response response =  given()
                .when()
                .get("/cgi-bin/welcome.pl?signOff=true")
                .then()
                .extract().response();
        String cookies;
        cookies = response.cookies().get("MSO");
        System.out.println(cookies);

        return  cookies;

    }

    @Step
    public static String goWebTours(RequestSpecification requestSpecification, ResponseSpecification responseSpecification){

        given()
                .spec(requestSpecification)
                .when()
                .get("/WebTours/")
                .then()
                .spec(responseSpecification);

        Response response =  given()
                .spec(requestSpecification)
                .when()
                .get("/cgi-bin/welcome.pl?signOff=true")
                .then()
                .spec(responseSpecification)
                .extract().response();
        String cookies;
        cookies = response.cookies().get("MSO");
        System.out.println(cookies);


        return  cookies;

    }

    @Step
    public static String getUserData (String login, String password, String sessionID, RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        String userData = "";
        ExtractableResponse<Response> response =
                given()
                        .spec(requestSpecification)
                        .body("userSession="+sessionID+"&username="+login+"&password="+password)
                        .when()
                        .post("/cgi-bin/login.pl")
                        .then()
                        .spec(responseSpecification)
                        .extract();
        System.out.println("Cookies: "+response.cookies().toString());
        return userData;

    }


    @Step
    public static String goWebTours(){


        given()
                .when()
                .get("http://localhost:1080/WebTours/")
                .then()
                .statusCode(200);

        Response response =  given()
                .when()
                .get("http://localhost:1080/cgi-bin/welcome.pl?signOff=true")
                .then()
                .statusCode(200)
                .extract().response();
        String cookies;
        cookies = response.cookies().get("MSO");
        System.out.println(cookies);


        return  cookies;

    }

    @Step
    public static String getUserData (String login, String password, String sessionID) {
        String userData = "";
        ExtractableResponse<Response> response =
                given()
                .contentType("application/x-www-form-urlencoded")
                .body("userSession="+sessionID+"&username="+login+"&password="+password)
                .when()
                .post("http://localhost:1080/cgi-bin/login.pl")
                .then()
                .statusCode(200)
                .extract();
               System.out.println("Cookies: "+response.cookies().toString());
        return userData;

    }



}
