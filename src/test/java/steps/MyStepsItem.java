package steps;

import factory.ApiClient;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.Base64;

import static org.hamcrest.Matchers.equalTo;

public class MyStepsItem {

    private ApiClient client = new ApiClient();
    private Response response;


    @Given("Tengo acceso a todo.ly")
    public void tengoAccesoATodoLy() {
        String credenciales = "paul277@gmail.com:t5ys6w";
        client.addHeaders("Authorization", "Basic " + Base64.getEncoder().encodeToString(credenciales.getBytes()));

    }

    @When("enviare un {word} request to {string} con body")
    public void enviareUnPOSTRequestToConBody(String method, String path, String body) {
        response= client.send(method, path, body);
    }

    @Then("el codigo de respuesta sera {int}")
    public void elCodigoDeRespuestaSera(int responseCodeExpected) {
        response.then().statusCode(responseCodeExpected);
        System.out.println("codigo de respuesta:" + responseCodeExpected);
    }

    @And("el atributo de {word} {string} es {string}")
    public void elatributodeStringEs(String type, String attribute, String expectedResult) {
        switch (type.toLowerCase()) {
            case "int" -> response.then().body(attribute, equalTo(Integer.parseInt(expectedResult)));
            case "boolean" -> response.then().body(attribute, equalTo(Boolean.parseBoolean(expectedResult)));
            case "string" -> response.then().body(attribute, equalTo(expectedResult));

        }
        System.out.println("el atributo es:" + attribute);
    }

    @And("el valor del {string} se guardara en la variable {string}")
    public void elValorDelSeGuardaraEnLaVariable(String jsonVariable, String variableName) {
        String value = response.jsonPath().getString(jsonVariable);
        client.addVariable(variableName, value);

    }
}
