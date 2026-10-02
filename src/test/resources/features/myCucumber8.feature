Feature: Login

  Scenario: Como usuario quiero ingresar un email y password para registrar usuarios
    Given que tengo acceso a instagram
    When me registro usando
      | nombre    | apellidos  | telefono | direccion | dni     |
      | juan      | perez      | 23432    | peru      | 32424   |
      | luis      | venegas      | 522157    | peru      | 9866   |
      | marco      | lopez      | 8422    | peru      | 765   |
    Then muestra la página principal