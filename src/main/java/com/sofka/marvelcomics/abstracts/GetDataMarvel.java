package com.sofka.marvelcomics.abstracts;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sofka.marvelcomics.ConnectionAPI;
import com.sofka.marvelcomics.MLogger;
import com.sofka.marvelcomics.models.ModelCharacter;

public abstract class GetDataMarvel {
    private static final ConnectionAPI connectionAPI = new ConnectionAPI();
    private static final MLogger mLog = new MLogger();

    protected GetDataMarvel() {
    }

    public static ModelCharacter getCharacter(String nameHero) {

        String dataAPI = connectionAPI.generateConnectionApiMarvel("characters", nameHero);
        Gson gson = new Gson();

        JsonObject jsonObjectAllData = gson.fromJson(dataAPI, JsonObject.class);
        JsonObject jsonObjectData = jsonObjectAllData.getAsJsonObject("data");

        if (jsonObjectData.get("total").getAsInt() > 0){
            JsonObject jsonObjectResult = jsonObjectData.get("results").getAsJsonArray().get(0).getAsJsonObject();

            return new ModelCharacter(
                    jsonObjectResult.get("id").getAsString(),
                    jsonObjectResult.get("name").getAsString(),
                    jsonObjectResult.get("description").getAsString());
        }else {
            mLog.otherData("No encontramos personajes con esa informacion, prueba nuevamente...");

            return null;
        }
    }

}
