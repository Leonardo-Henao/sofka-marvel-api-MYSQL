package com.sofka.marvelcomics;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class ControladorProperties  {

    protected static final Properties properties = new Properties();
    public static final MLogger mlog = new MLogger();

    public String mGetProperties(String searchPropertie) {

        String propTemp = properties.getProperty(searchPropertie);

        if (propTemp == null) {
            try (FileReader fileReader = new FileReader("src/main/resources/config.properties")) {
                properties.load(fileReader);
                propTemp = properties.getProperty(searchPropertie);

            } catch (IOException e) {
                mlog.showErrorMessage(e);
            }
        }

        return propTemp;
    }
}
