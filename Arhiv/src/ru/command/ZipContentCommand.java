package ru.command;

import ru.ConsoleHelper;
import ru.FileProperties;

import java.util.List;

public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {

        List<FileProperties> list = getZipFileManager().getFilesList();
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ConsoleHelper.writeMessage("Содержимое архива:");
        for (FileProperties f : list) {
            ConsoleHelper.writeMessage(f.toString());
        }
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");
    }
}
