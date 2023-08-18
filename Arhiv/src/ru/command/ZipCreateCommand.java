package ru.command;


import ru.ConsoleHelper;
import ru.ZipFileManager;
import ru.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ZipCreateCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("�������� ������.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("������� ������ ��� ����� ��� ���������� ��� ���������:");
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.createZip(sourcePath);

            ConsoleHelper.writeMessage("����� ������.");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("�� ������� ������� ��� ����� ��� ����������.");
        }
    }
}
