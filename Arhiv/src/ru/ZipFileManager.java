package ru;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }
    public void createZip(Path source) throws Exception{
        String srczipFile = source.getFileName().toString();
        try (
                ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile));
                InputStream is = Files.newInputStream(source)
        ) //Создай новый.
        // поток архива ZipOutputStream используя переменную класса zipFile,
        // с помощью метода newOutputStream класса Files.{
        {
            ZipEntry zipEntry = new ZipEntry(srczipFile);//Создай новый элемент архива ZipEntry
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1000];
            while (is.available()>0){
                int count = is.read(buffer);
                zos.write(buffer,0,count);
            }
        }
    }
}