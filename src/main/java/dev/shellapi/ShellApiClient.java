package dev.shellapi;

import net.fabricmc.api.ClientModInitializer;

public class ShellApiClient implements ClientModInitializer {

    public static final String ROOT = "api_apps";

    @Override
    public void onInitializeClient() {
        util.FileUtil.ensureDir(ROOT);
        intercept.ChatInterceptor.register();
    }
}
