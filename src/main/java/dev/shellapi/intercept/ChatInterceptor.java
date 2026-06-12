package dev.shellapi.intercept;

import dev.shellapi.runtime.CommandResolver;
import net.fabricmc.fabric.api.client.networking.v1.ClientSendMessageEvents;

public class ChatInterceptor {

    public static void register() {

        ClientSendMessageEvents.ALLOW_CHAT.register((message) -> {

            if (!message.startsWith("/")) {
                return true;
            }

            String raw = message.substring(1);
            String[] split = raw.split(" ");

            String command = split[0];

            String[] args = new String[Math.max(0, split.length - 1)];
            System.arraycopy(split, 1, args, 0, args.length);

            boolean handled = CommandResolver.tryExecute(command, args);

            return !handled;
        });
    }
}
