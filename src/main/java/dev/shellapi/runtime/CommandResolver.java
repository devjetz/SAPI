package dev.shellapi.runtime;

import java.nio.file.*;
import java.io.File;

public class CommandResolver {

    private static final String ROOT = "api_apps";

    public static boolean tryExecute(String command, String[] args) {

        Path path = Paths.get(ROOT, command + ".json");

        if (!Files.exists(path)) {
            return false; // allow Minecraft to send it
        }

        CommandDefinition def = JsonLoader.load(path.toFile());

        if (def == null) {
            return false;
        }

        if (!def.validateArgs(args)) {
            return true; // block command even if invalid
        }

        ProcessRunner.run(def, args);

        return true;
    }
}
