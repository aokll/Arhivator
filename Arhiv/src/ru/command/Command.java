package ru.command;

public interface Command {
    default void execute() throws Exception{}
}
