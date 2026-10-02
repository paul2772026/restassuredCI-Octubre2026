Feature: Login

  Scenario: Como usuario quiero ingresar un email y password para registrar usuarios
    Given que tengo acceso a instagram
    When me registro usando
      | nombre    | apellidos  | telefono | direccion | dni     |
      | juan      | perez      | 23432    | peru      | 32424   |
    Then muestra la pagina principal