package com.sofka.marvelcomics;

import java.util.Scanner;

public class Home {
    public static final MLogger mLog = new MLogger();
    private static final ControladorDB getFromDB = new ControladorDB();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        mLog.openApp();

        boolean closeApp = false;

        while (!closeApp) {

            mLog.showMenu();
            verificarDatoEntrada();
            Integer opcionSeleccionada = scanner.nextInt();

            switch (opcionSeleccionada) {
                case 1:
                    getFromDB.readDB();
                    break;
                case 2:
                    mLog.otherData("Ingresa el nombre del Heroe que deseas agregar.");
                    String nameCharacter = scanner.next();
                    getFromDB.createInDB(nameCharacter);
                    break;
                case 3:
                    break;
                case 4:
                   // verificarDatoEntrada();
                    mLog.otherData("Ingresa ID del Heroe que deseas eliminar.");
                    Integer idCharacter = scanner.nextInt();
                    getFromDB.deleteInDB(idCharacter);
                    break;
                case 5:
                    closeApp = true;
                    break;
                default:
                    mLog.otherData("Debes seleccionar una opcion valida");
                    break;

            }

        }

    }

    private static void verificarDatoEntrada() {
        while (!scanner.hasNextInt()) {
            mLog.otherData("Ingresa un valor NUMERICO:");
            scanner.next();
        }
    }
}
