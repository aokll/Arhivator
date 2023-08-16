package ru;

import ru.command.*;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command>ALL_KNOWN_COMMANDS_MAP = new HashMap<>();

    static{//поскольку хранилище создается только 1 раз то лучше это сделать
        //в статическом блоке.
      ALL_KNOWN_COMMANDS_MAP.put(Operation.EXIT, new ExitCommand());
      ALL_KNOWN_COMMANDS_MAP.put(Operation.ADD, new ZipAddCommand());
      ALL_KNOWN_COMMANDS_MAP.put(Operation.CREATE, new ZipCreateCommand());
      ALL_KNOWN_COMMANDS_MAP.put(Operation.CONTENT, new ZipContentCommand());
      ALL_KNOWN_COMMANDS_MAP.put(Operation.EXTRACT, new ZipExtractCommand());
      ALL_KNOWN_COMMANDS_MAP.put(Operation.REMOVE, new ZipRemoveCommand());
    }
    private CommandExecutor(){}//чтобы запретить явный вызов конструктора данного класса
    //просто создадим пустой приватный конструктор.

    public static void execute(Operation operation) throws Exception{
        for (Operation key : ALL_KNOWN_COMMANDS_MAP.keySet()) {
            if (key == operation){
                Command command = ALL_KNOWN_COMMANDS_MAP.get(key);
                command.execute();
                //В данном методемы перебираем коллекцию Map и по ключам получаем значения
                //и у значений вызываем соответсвующий метод.
            }
        }
    }
    //ЗДЕСЬ МЫ РЕАЛИЗОВАЛИ ПАТТЕРН "КОМАНДА"
}
