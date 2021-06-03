package course.advance;

import course.advance.command.*;
import course.advance.command.result.CommandResult;
import course.advance.handler.ConsoleHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    /**
     * @return Set of available commands
     */
    private static Set<ConsoleCommand> getAvailableCommands() {
        Set<ConsoleCommand> commands = new HashSet<>();
        commands.add(new ChangeDirectory());
        commands.add(new ListOfFiles());
        commands.add(new Rename());
        commands.add(new Copy());
        commands.add(new Print());
        commands.add(new Help(commands));
        return commands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleHandler handler = new ConsoleHandler(getAvailableCommands());
        Path current = Paths.get(System.getProperty("user.dir")).getRoot();
        System.out.println(current);
        while (scanner.hasNext()) {
            try {
                String line = scanner.nextLine();
                CommandResult result = handler.searchAndExecute(current, line);
                if (result.isSuccessful()) {
                    current = result.getNewPath().normalize();
                    System.out.println(result.getMessage());
                } else
                    System.err.println(result.getMessage());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }

}
