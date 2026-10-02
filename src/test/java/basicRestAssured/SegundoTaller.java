package basicRestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SegundoTaller {

    static String token;

    private static int projectId;


    @BeforeAll
    static void init() throws IOException {
        Properties props = new Properties();
        InputStream inputStream = TokenProjectApi.class
                .getClassLoader()
                .getResourceAsStream("usuarios.properties");

        props.load(inputStream);

        Response response =
                given()
                        .auth()
                        .preemptive()
                        .basic(props.getProperty("api.usuario"), props.getProperty("api.clave"))
                        .log().all()
                        .when()
                        .get("https://todo.ly/api/authentication/token.json")
                        .then()
                        .statusCode(200)
                        .extract().response();

        token = response.jsonPath().getString("TokenString");

    }


    @Order(1)
    @CsvFileSource(resources = "/csv/crear.csv", numLinesToSkip = 1)
    @ParameterizedTest
    void crear(String proyecto, int icono, int codigoRespuesta) {
        JSONObject payload = new JSONObject();
        payload.put("Content", proyecto);
        payload.put("Icon", icono);

        Response response =
                given()
                        .header("Token", token)
                        .body(payload.toString())
                        .log()
                        .all()
                        .when()
                        .post("https://todo.ly/api/projects.json")
                        .then()
                        .log().all()
                        .statusCode(codigoRespuesta)
                        .body("Content", equalTo(proyecto))
                        .body("Icon", equalTo(icono))
                        .extract().response();

        projectId = response.jsonPath().getInt("Id");
        System.out.println("*** projectId: " + projectId);
    }

//@CsvFileSource(resources = "/csv/actualizar.csv", numLinesToSkip = 1)
//@ParameterizedTest

    @Order(2)
    @Test
    void actualizar () {
        JSONObject payload = new JSONObject();
        payload.put("Content", "Serenity Modificado");
        payload.put("Icon", 4);


        given()
                .header("Token", token)
                .body(payload.toString())
                .log()
                .all()
                .when()
                .put("https://todo.ly/api/projects/" + projectId + ".json")
                .then()
                .log().all()
                // .body("Id", greaterThan(0))
                .statusCode(200)
                .body("Content", equalTo("Serenity Modificado"))
                .body("Icon", equalTo(4));

        System.out.println("*** projectId: " + projectId);

    }

    @Order(3)
    @Test
    void buscarPorId() {

        given()
                .header("Token", token)
                .log()
                .all()
                .when()
                .get("https://todo.ly/api/projects/" + projectId + ".json")
                .then()
                .log().all()
                .body("Id", greaterThan(0))
                .statusCode(200)
                .body("Content", equalTo("Serenity Modificado"))
                .body("Icon", equalTo(4));

    }
//
//    @Order(4)
//    @Test
//    void eliminar() {
//
//        given()
//                .header("Token", token)
//                .log()
//                .all()
//                .when()
//                .delete("https://todo.ly/api/projects/" + projectId + ".json")
//                .then()
//                .log().all()
//                .statusCode(200)
//                .body("Content", equalTo("Serenity Modificado"))
//                .body("Icon", equalTo(4))
//                .body("Deleted", equalTo(true));
//
//    }




}
