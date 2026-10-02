Feature: Item API

  @projects
  Scenario: Como usuario quiero hacer el CRUD de un Item por API

    Given Tengo acceso a todo.ly
    When enviare un POST request to "/api/items.json" con body
    """
    {
    "Content": "Laptop"
    }
    """
    Then el codigo de respuesta sera 200
    And el atributo de string "Content" es "Laptop"
    And el valor del "Id" se guardara en la variable "projectId"

    When enviare un PUT request to "/api/items/{projectId}.json" con body
     """
    {
    "Content": "Laptop Nueva"
    }
    """
    Then el codigo de respuesta sera 200
    And el atributo de string "Content" es "Laptop Nueva"

    When enviare un GET request to "/api/items/{projectId}.json" con body
    """
    """
    Then el codigo de respuesta sera 200
    And el atributo de string "Content" es "Laptop Nueva"

    When enviare un DELETE request to "/api/items/{projectId}.json" con body
    """
    """
    Then el codigo de respuesta sera 200
    And el atributo de string "Content" es "Laptop Nueva"


