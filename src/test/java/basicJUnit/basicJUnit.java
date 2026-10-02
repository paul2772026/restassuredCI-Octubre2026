package basicJUnit;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class basicJUnit {

    @BeforeEach
    void inicializar(){
        System.out.println("Este metodo se ejecuta antes de cada test...");

    }

    @AfterEach
    void Limpiar(){
        System.out.println("Este metodo se ejecuta despues de cada test...");

    }

    @Order(1)
    @Test
    void crearProyecto(){

        System.out.println("Este es un test para crear el proyecto");
    }

    @Order(2)
    @Test
    void actualizarProyecto(){
        System.out.println("Este es un test que verifica la actualizacion de un proyecto");

    }
}
