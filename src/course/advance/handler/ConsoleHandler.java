package course.advance.handler;

import course.advance.command.ConsoleCommand;
import course.advance.command.result.CommandResult;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsoleHandler {

    private final Set<ConsoleCommand> commands;

    public ConsoleHandler(Set<ConsoleCommand> commands) {
        this.commands = commands;
    }

    public CommandResult searchAndExecute(Path current, String input) throws IOException {
        ConsoleCommand command = searchCommand(input);
        if (command != null)
            return command.execute(current, input);
        Set<ConsoleCommand> possibleCommands = getPossibleCommands(input);
        if (possibleCommands != null && !possibleCommands.isEmpty())
            return constructPossibleCommandsResult(current, possibleCommands);
        else
            return constructMissedCommandResult(current, input);
    }

    private CommandResult constructMissedCommandResult(Path current, String input) {
        String message = String.format("'%s' - isn't command. Print 'help' for getting commands", input);
        return new CommandResult(false, current, message);
    }

    private CommandResult constructPossibleCommandsResult(Path current, Set<ConsoleCommand> possibleCommands) {
        String commandsInfo = possibleCommands.stream()
                .map(ConsoleCommand::getInfo)
                .collect(Collectors.joining("\n"));
        String message = "Did you mean: \n" + commandsInfo + "?";
        return new CommandResult(false, current, message);
    }

    public ConsoleCommand searchCommand(String input) {
        return commands.stream().filter(c -> c.validate(input))
                .findAny()
                .orElse(null);
    }

    private Set<ConsoleCommand> getPossibleCommands(String input) {
        return commands.stream().filter(c -> c.suggest(input))
                .collect(Collectors.toSet());
    }

}
