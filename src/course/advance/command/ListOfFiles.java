package course.advance.command;

import course.advance.command.result.CommandResult;
import course.advance.utils.DirectoryUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * Command for printing content in current directory
 */
public class ListOfFiles extends AbstractConsoleCommand implements ConsoleCommand {

    private static final String infoMessage = "'ls' - for getting list of files in current directory";
    private static final Pattern suggestPattern = Pattern.compile("^\\s*[ls]{2}\\s*$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern validatePattern = Pattern.compile("^\\s*ls\\s*$",
            Pattern.CASE_INSENSITIVE);

    public ListOfFiles() {
        super(infoMessage, suggestPattern, validatePattern);
    }

    @Override
    public CommandResult execute(Path current, String input) throws IOException {
        String message = DirectoryUtils.getDirectoryContent(current);
        return new CommandResult(true, current, message);
    }
}
