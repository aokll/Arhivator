package ru.command;

import ru.ConsoleHelper;

public class ExitCommand extends ConsoleHelper implements Command {
    @Override
    public void execute() throws Exception {
        System.out.println("До встречи!");
    }
}
