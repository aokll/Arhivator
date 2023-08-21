package ru.command;



import ru.ConsoleHelper;
import ru.ZipFileManager;
import ru.exception.WrongZipFileException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Распаковка архива.");


        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите полное имя файла или директории куда будет распоковываться архив:");
        Path path = Paths.get(ConsoleHelper.readString());
        zipFileManager.extractAll(path);

        ConsoleHelper.writeMessage("Архив рапокован.");
    } catch (WrongZipFileException e) {
        ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
    }
    }
}
