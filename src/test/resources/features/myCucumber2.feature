Feature: Login

  Scenario Outline: Como usuario quiero ingresar un email y password para iniciar sesión

    Given que tengo acceso a instagram
    When ingreso mi email: <email>
    And ingreso mi password: "<password>"
    Then hago clic en el boton iniciar sesion
    And muestra la pagina principal

    Examples:
      | email | password |
      | usuario1@gmail.com | admin123 |
      | usuario2@gmail.com | 123456   |
      | usuario3@gmail.com | 3333     |