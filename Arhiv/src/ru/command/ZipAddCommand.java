package ru.command;

import ru.exception.*;
import ru.ConsoleHelper;
import ru.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipAddCommand extends ZipCommand{//Команда добавления файла в архив
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Добавление файла в архив.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите полный путь файла в архиве:");
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.addFile(sourcePath);

            ConsoleHelper.writeMessage("Добавление файла в архив завершено.");
        }catch (PathIsNotFoundException e) {
            e.printStackTrace();
        }
    }
}
