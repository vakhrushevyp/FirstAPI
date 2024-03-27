package in.regress;

import data.People;
import data.PeopleCreated;
import data.UserData;
import data.UsersFromPage;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.jsoup.Connection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APITests {

    @Test
    public void checkFieldsNotNull(){

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .body("page",notNullValue())
                .body("per_page",notNullValue())
                .body("total;", notNullValue())
                .body("total_pages",notNullValue())
                .body("data.id", not(hasItem(nullValue())))
                .body("data.first_name",hasItem("Lindsay"));

    }

    @Test
    public void createUser(){
        Map<String, String> requestData = new HashMap<>();
        requestData.put("name", "Kirill");
        requestData.put("job","teacher");
        Response response =  given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("name").toString(),
                requestData.get("name"),"name is not valid");
        Assert.assertEquals(jsonResponse.get("job").toString(),
                requestData.get("job"),"name is not valid");

    }

    @Test
    public void createUserWithDTO(){
        People people = new People("Kirill", "teacher");

        PeopleCreated peopleCreated = given()
                .contentType("application/json")
                .body(people)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(PeopleCreated.class);
        System.out.println(peopleCreated.getId());

    }

    @Test
    public void usersFromPageDTO(){
        UsersFromPage usersFromPage =
                given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .extract().as(UsersFromPage.class);
        System.out.println(usersFromPage.getSupport().getText());



    }

    @Test
    public void usersFromPageList(){
        List<UserData> userData =
                given()
                        .when()
                        .get("https://reqres.in/api/users?page=2")
                        .then()
                        .log().all()
                        .extract().body().jsonPath().getList("data", UserData.class);
        userData.forEach(x-> System.out.println(x.getFirst_name()));


    }

    @Test
    public void goWebTours() {

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






        given()

                .when()
                .get("http://localhost:1080/cgi-bin/nav.pl?in=home")
                .then()
                .statusCode(200);


    }

    }
