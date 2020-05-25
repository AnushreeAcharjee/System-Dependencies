package com.apple.controller;

import com.apple.model.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by dell on 25 May, 2020
 */
public class TestCommandFactory {
    CommandFactory commandFactory;

    @BeforeEach
    public void init(){
        commandFactory = new CommandFactory();
    }

    @Test
    public void testGetCommand(){
        Optional<Command> command = CommandFactory.getCommand("depend");
        assertEquals(Command.DEPEND,command.get());
    }

    @Test
    public void testGetCommand_nonexisting(){
        Optional<Command> command = CommandFactory.getCommand("finish");
        assertEquals(Optional.empty(),command);
    }
}
