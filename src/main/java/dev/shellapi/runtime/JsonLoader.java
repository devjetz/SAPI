package dev.shellapi.runtime;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;

public class JsonLoader {

    private static final Gson gson = new Gson();

    public static CommandDefinition load(File file) {
        try (FileReader r = new FileReader(file)) {
            return gson.fromJson(r, CommandDefinition.class);
        } catch (Exception e) {
            return null; // ignore broken JSON completely
        }
    }
}
