package course.advance.command;

import course.advance.command.result.CommandResult;

import java.nio.file.Path;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Commands for printing available commands
 */
public class Help extends AbstractConsoleCommand implements ConsoleCommand {

    private static final String infoMessage = "'help' - for getting list of available commands";
    private static final Pattern suggestPattern = Pattern.compile("[help]{2,4}",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern validatePattern = Pattern.compile("^\\s*help\\s*$",
            Pattern.CASE_INSENSITIVE);

    private final Set<ConsoleCommand> commands;

    public Help(Set<ConsoleCommand> commands) {
        super(infoMessage, suggestPattern, validatePattern);
        this.commands = commands;
    }

    @Override
    public boolean validate(String input) {
        return validatePattern.matcher(input).find();
    }

    @Override
    public CommandResult execute(Path current, String input) {
        String message = commands.stream().map(ConsoleCommand::getInfo)
                .collect(Collectors.joining("\n"));
        return new CommandResult(true, current, message);
    }
}
