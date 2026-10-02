Feature: Login

  Scenario: Como usuario quiero ingresar un email y password para iniciar sesion
    Given que tengo acceso a instagram
    When ingreso mi email: alt.carlos@gmail.com
    And ingreso mi password: "admin123"
    Then hago clic en el boton iniciar sesion
    And muestra la pagina principal
    And deberia ver los siguientes menus
      | Configuracion |
      | Publicaciones |
      | Ajustes       |
      | Perfil        |
      | Contactos     |