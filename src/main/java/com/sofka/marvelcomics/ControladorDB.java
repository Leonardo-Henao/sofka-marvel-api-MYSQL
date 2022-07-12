package com.sofka.marvelcomics;

import com.sofka.marvelcomics.abstracts.GetDataMarvel;
import com.sofka.marvelcomics.interfaces.interfaceControladorDB;
import com.sofka.marvelcomics.models.ModelCharacter;
import java.sql.*;
import java.util.ArrayList;

public class ControladorDB extends GetDataMarvel implements interfaceControladorDB {
    private static final MLogger mLog = new MLogger();
    private static final ControladorProperties properties = new ControladorProperties();

    public void readDB() {

        try (Connection connecSQL = setdriver()) {

            try (Statement statement = connecSQL.createStatement()) {

                ResultSet resultQR = statement.executeQuery("SELECT * FROM `character` WHERE 1");

                ArrayList<ModelCharacter> listCharacters = new ArrayList<>();

                while (resultQR.next()) {
                    ModelCharacter modelTemp = new ModelCharacter(
                            resultQR.getString("id"), resultQR.getString("name"),
                            resultQR.getString("description"));
                    listCharacters.add(modelTemp);
                }
                if (!listCharacters.isEmpty()) mLog.showCharacterData(listCharacters);
                else mLog.otherData("No se encontraron personajes");
            }


        } catch (Exception error) {
            mLog.showErrorMessage(error);
        }
    }

    @Override
    public void createInDB(String nameCharacter) {

        ModelCharacter character = getCharacter(nameCharacter);

        if (character != null) {
            try (Connection connecSQL = setdriver()) {

                try (PreparedStatement statement = connecSQL.prepareStatement("INSERT INTO `character` (`name`, `description`)  VALUES (?, ?)")) {

                    String description = character.getDescription();
                    if (description.isEmpty()){
                        description = "No tenemos informacion sobre este personaje, muy pronto la tendremos!";
                    }

                    statement.setString(1, character.getName());
                    statement.setString(2, description);
                    statement.execute();

                    mLog.otherData("Agregamos a " + character.getName() + ", perfecto!");
                }

            } catch (Exception error) {
                mLog.showErrorMessage(error);
            }
        }
    }

    @Override
    public void deleteInDB(Integer idCharacter) {

        try (Connection connecSQL = setdriver()) {

            try (PreparedStatement statement = connecSQL.prepareStatement("DELETE FROM `character` WHERE `id` =?")) {
                statement.setInt(1, idCharacter);
                statement.execute();

                if (statement.execute()) mLog.otherData("Personaje eliminado correctamente.");
                else mLog.otherData("No se encontraron personajes.");
            }

        } catch (Exception error) {
            mLog.showErrorMessage(error);
        }
    }

    private static Connection setdriver() throws SQLException {
        return DriverManager.getConnection(
                properties.mGetProperties("URL_DB"),
                properties.mGetProperties("USER_DB"),
                properties.mGetProperties("PASSWORD_DB"));
    }
}
