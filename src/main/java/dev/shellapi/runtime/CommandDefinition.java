package dev.shellapi.runtime;

import java.util.List;

public class CommandDefinition {

    public String shell;
    public List<Arg> arguments;

    public static class Arg {
        public String name;
        public boolean required;
        public boolean greedy;
    }

    public boolean validateArgs(String[] args) {

        if (arguments == null) return true;

        int requiredCount = 0;

        for (Arg a : arguments) {
            if (a.required) requiredCount++;
        }

        if (args.length < requiredCount) {
            return false;
        }

        return true;
    }
}
