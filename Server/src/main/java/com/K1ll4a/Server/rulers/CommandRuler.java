package com.K1ll4a.Server.rulers;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.K1ll4a.Server.commands.Command;

import java.util.LinkedHashMap;

public class CommandRuler {
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    public void register(String commandName, Command command){
        commands.put(commandName, command);
    }

    public Map<String, Command> getCommands(){
        return commands;
    }

    public List<String> getCommandHistory(){
        return commandHistory;
    }

    public void addTohistory(String command){
        commandHistory.add(command);
    }

}