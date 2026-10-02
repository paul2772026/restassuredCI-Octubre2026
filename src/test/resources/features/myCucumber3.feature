Feature: Login

  Background: init
    Given que tengo acceso a instagram
    When ingreso mi email: alt.carlos@gmail.com

Rule: Credenciales Validas
  Scenario: Como usuario quiero ingresar un email y password para iniciar sesion
    And ingreso mi password: "admin123"
    Then hago clic en el boton iniciar sesion
    And muestra la pagina principal

Rule:Credenciales Invalidas
  Scenario: Como usuario quiero ingresar un email sin password para tener alguna validacion
    Then hago clic en el boton iniciar sesion
    And no muestra la pagina principal

  Scenario: Como usuario quiero ingresar un email y password pero no hago clic en el boton iniciar sesion
    And ingreso mi password: "admin123"
    And no muestra la pagina principal