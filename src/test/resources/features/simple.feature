@list_apk_info
Feature: Apk info Funcionalidad listar la informacion de mis apps

  @CP1_list_apk_info
  Scenario: Listar la informacion de mis apps
    Given El usuario se encuentra en la pantalla principal
    When El usuario selecciona la opcion de la lista de aplicaciones "AFP Integra"
    Then El usuario visualiza la informacion del app instalado

  @CP2_list_apk_info
  Scenario: Listar la informacion del app Llamadas
    Given El usuario se encuentra en la pantalla principal
    When El usuario selecciona la opcion de la lista de aplicaciones "Llamadas"
    Then El usuario visualiza la informacion del app instalado

  @CP3_list_apk_info
  Scenario Outline: Listar la informacion de mis apps con datos
    Given El usuario se encuentra en la pantalla principal
    When El usuario selecciona la opcion de la lista de aplicaciones "<app_name>"
    Then El usuario visualiza la informacion del app instalado

    Examples:
      | app_name      |
      | Mensajes      |
      | Cámara        |
      | Configuración |
      | Galería       |
      | Reloj         |
      | Calculadora   |
      | Contactos     |
      | Música        |
      | Navegador     |
      | Correo        |