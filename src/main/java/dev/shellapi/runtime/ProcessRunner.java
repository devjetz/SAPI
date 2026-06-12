package dev.shellapi.runtime;

import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProcessRunner {

    public static void run(CommandDefinition def, String[] args) {

        try {
            List<String> cmd = new ArrayList<>();

            // split shell into base command
            String[] base = def.shell.split(" ");
            for (String b : base) cmd.add(b);

            boolean greedy = def.arguments != null
                    && !def.arguments.isEmpty()
                    && def.arguments.get(def.arguments.size() - 1).greedy;

            if (greedy && args.length >= def.arguments.size()) {

                StringBuilder sb = new StringBuilder();

                for (int i = def.arguments.size() - 1; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }

                cmd.add(sb.toString().trim());

            } else {
                for (String arg : args) {
                    cmd.add(arg);
                }
            }

            ProcessBuilder pb = new ProcessBuilder(cmd);

            pb.directory(new File("api_apps"));

            // environment context
            var env = pb.environment();

            MinecraftClient mc = MinecraftClient.getInstance();

            if (mc.player != null) {
                env.put("MC_PLAYER", mc.player.getName().getString());
            }

            pb.start();

        } catch (Exception ignored) {
            // silent failure by design
        }
    }
}
