package com.sofka.marvelcomics.interfaces;

import com.sofka.marvelcomics.models.ModelCharacter;
import java.util.ArrayList;

public interface InterfaceLogger {
    void openApp();

    void otherData(String message);

    void showCharacterData(ArrayList<ModelCharacter> data);

    void showErrorMessage(Exception error);
}
