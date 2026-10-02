Feature: Project API

  Scenario: Como usuario quiero hacer el CRUD de un proyecto por API
    #crear
    Given que tengo acceso al API todo.ly
    When envio el POST request a la url "https://todo.ly/api/projects.json" con el body
    """
    {
      "Content": "Cucumber",
      "Icon": 10
    }
    """
    Then el codigo de respuesta deberia ser 200
    And el nombre del proyecto deberia ser "Cucumber"
    And el icono del proyecto deberia ser 10
    And guardo el id del proyecto de la variable "Id"

    #actualizar
    When envio el PUT request a la url "https://todo.ly/api/projects/PROJECT_ID.json" con el body
    """
    {
      "Content": "CucumberUpdated",
      "Icon": 11
    }
    """
    Then el codigo de respuesta deberia ser 200
    And el nombre del proyecto deberia ser "CucumberUpdated"
    And el icono del proyecto deberia ser 11

        #leer
    When envio el GET request a la url "https://todo.ly/api/projects/PROJECT_ID.json"
    Then el codigo de respuesta deberia ser 200
    And el nombre del proyecto deberia ser "CucumberUpdated"
    And el icono del proyecto deberia ser 11

    #eliminar
    When envio el DELETE request a la url "https://todo.ly/api/projects/PROJECT_ID.json"
    Then el codigo de respuesta deberia ser 200
    And el nombre del proyecto deberia ser "CucumberUpdated"
    And el icono del proyecto deberia ser 11