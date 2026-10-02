package basicRestAssured;


import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssured {

    /**
     * given() -> controla la configuración de la petición
     *            - headers
     *            - parámetros
     *            - body/payload
     *            - authorization
     *            - logs
     *
     *  when() -> especificamos el metodo: GET | POST | PUT | DELETE
     *            especificamos la url => ip:puerto | hostname
     *
     *  then() -> controlar la respuesta
     *             - response code
     *             - response body
     *             - response header
     *             - response size
     *             - response time
     *            implementar verificaciones
     *            implementar extracciones (extraer valores del response y asignarlo a variables)
     *            logs
     */

    @Test
    void crearProyecto() {
        given()
                .auth()
                .preemptive()
                .basic("paul277@gmail.com", "t5ys6w")
                .body("""
                        {
                            "Content": "RestAssured",
                            "Icon": 5
                        }
                        """)
                .log()
                .all()
                .when()
                .post("https://todo.ly/api/projects.json");

    }

    @Test
    void crearProyectoUsandoJsonObject() {
        JSONObject payload = new JSONObject();
        payload.put("Content", "RestAssuredJsonObject");
        payload.put("Icon", 3);

        given()
                .auth()
                .preemptive()
                .basic("paul277@gmail.com", "t5ys6w")
                .body(payload.toString())
                .log()
                .all()
                .when()
                .post("https://todo.ly/api/projects.json");

    }

    @Test
    void crearProyectoUsandoJsonFile() {

        String rutaJsonFile = getClass()
                .getClassLoader()
                .getResource("projects.json")
                .getPath();


        System.out.println("ruta: " + rutaJsonFile);

        given()
                .auth()
                .preemptive()
                .basic("paul277@gmail.com", "t5ys6w")
                .body(new File(rutaJsonFile))
                .log()
                .all()
                .when()
                .post("https://todo.ly/api/projects.json");

    }


    @Test
    void crearProyectoConVerificaciones() {
        JSONObject payload = new JSONObject();
        payload.put("Content", "RestAssuredV3");
        payload.put("Icon", 6);

        Response response =
        given()
                .auth()
                .preemptive()
                .basic("paul277@gmail.com", "t5ys6w")
                .body(payload.toString())
                .log()
                .all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
               // .log().all()
                .log().ifValidationFails()
                .statusCode(200)
                .body("Content", equalTo( "RestAssuredV3"))
                .body("Icon", equalTo(6))
                .body("Icon" ,equalTo(6))
                .time(Matchers.lessThan(5000L))
        .extract().response();

        int id = response.jsonPath().getInt("Id");
        String content = response.jsonPath().getString("Content");
        int icon = response.jsonPath().getInt("Icon");

        System.out.println("Id: " + id);
        System.out.println("Content: " + content);
        System.out.println("Icon: " + icon);

    }

}
