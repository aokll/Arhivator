package ru.command;

import ru.ConsoleHelper;

public class ExitCommand extends ZipCommand implements Command{
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("До встречи!");
    }
}
