package com.sofka.marvelcomics;

import com.sofka.marvelcomics.interfaces.InterfaceConnectionAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;

public class ConnectionAPI implements InterfaceConnectionAPI {
    private final Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    private final OkHttpClient httpClient = new OkHttpClient();
    private static final ControladorProperties properties = new ControladorProperties();
    public String generateConnectionApiMarvel(String typeSearch, String nameCharacter) {

        try {
            String setSearch = typeSearch + "?nameStartsWith=" + nameCharacter;
            String setTimestamp = "&ts=" + timeStamp.getTime();
            String setLimitSearch = "&limit=" + 1;
            String setApikey = "&apikey=" + properties.mGetProperties("PUBLICKEY");
            String setHash = "&hash=" + generateHash();

            String urlCompose = properties.mGetProperties("URLAPI") + setSearch + setLimitSearch + setTimestamp + setApikey + setHash;

            Request request = new Request
                    .Builder()
                    .url(urlCompose)
                    .build();

            Response responseBody = httpClient.newCall(request).execute();

            assert responseBody.body() != null;
            return responseBody.body().string();

        } catch (Exception error) {
            mLog.showErrorMessage(error);
            return null;
        }
    }

    @Override
    public String generateHash() {

        try {
            // GENERATE BYTE ARRAY
            String constructorApiHash = timeStamp.getTime() + properties.mGetProperties("PRIVATEKEY")
                    + properties.mGetProperties("PUBLICKEY");

            byte[] bytesConstructorApiHash = constructorApiHash.getBytes(StandardCharsets.UTF_8);
            MessageDigest diggest = MessageDigest.getInstance("MD5");

            //CONVERT BYTE ARRAY TO STRING
            StringBuilder hash = new StringBuilder();
            byte[] mDiggest = diggest.digest(bytesConstructorApiHash);

            for (byte b : mDiggest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) hash.append(0);
                hash.append(hex);
            }

            return hash.toString();

        } catch (Exception error) {
            mLog.showErrorMessage(error);

            return "null";
        }
    }
}

