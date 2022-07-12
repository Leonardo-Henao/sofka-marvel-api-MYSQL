package com.sofka.marvelcomics.interfaces;

import com.sofka.marvelcomics.MLogger;

public interface InterfaceConnectionAPI {
    MLogger mLog = new MLogger();
    String generateConnectionApiMarvel(String typeSearch, String idCharacter);
    String generateHash();
}
