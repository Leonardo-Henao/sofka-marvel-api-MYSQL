package com.sofka.marvelcomics;

import com.sofka.marvelcomics.interfaces.InterfaceLogger;
import com.sofka.marvelcomics.models.ModelCharacter;
import org.jboss.logging.Logger;

import java.util.ArrayList;

public class MLogger implements InterfaceLogger {
    private final Logger log = Logger.getLogger(MLogger.class);

    @Override
    public void openApp() {
        log.info("""
                                
                +-------------------------------------+
                |      WELCOME TO MARVEL HEROES       |
                +-------------------------------------+
                """);
    }

    @Override
    public void otherData(String message) {
        log.info(message);
    }

    @Override
    public void showCharacterData(ArrayList<ModelCharacter> data) {

        data.forEach(character -> log.info("\n" +
                "+-----------------------------+\n" +
                "\\  ID: " + character.getId() + "\n" +
                " | Hero Name: " + character.getName() + "\n" +
                "/  Description: " + character.getDescription() + "\n" +
                "+-----------------------------+\n"));
    }

    @Override
    public void showErrorMessage(Exception error) {
        log.error(error.getLocalizedMessage() + " -> " + error.getMessage());
    }

    public void showMenu() {
        log.info("""
                                
                +-------------------------------------+
                |       SELECCIONA UNA OPCION         |
                +-------------------------------------+
                |   [1] Ver heroes guardados          |
                |   [2] Agregar un nuevo heroe        |
                |   [3] Actualizar sus datos          |
                |   [4] Eliminar                      |
                |   [5] Cerrar aplicacion             |
                +-------------------------------------+
                """);
    }
}
