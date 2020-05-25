package com.apple.controller;

import com.apple.model.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by anushree on 24 May, 2020
 */
public class CommandFactory {
    static Map<String, Command> commandMap = new HashMap<>();
    static {
        commandMap.put("depend", Command.valueOf("DEPEND"));
        commandMap.put("install", Command.valueOf("INSTALL"));
        commandMap.put("remove", Command.valueOf("REMOVE"));
        commandMap.put("list", Command.valueOf("LIST"));
        commandMap.put("end", Command.valueOf("END"));
        // more commands
    }

    public static Optional<Command> getCommand(String operator) {
        return Optional.ofNullable(commandMap.get(operator));
    }
}
