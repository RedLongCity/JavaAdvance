package course.advance.command;

import course.advance.command.result.CommandResult;
import course.advance.utils.DirectoryUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command for printing file content
 */
public class Print extends AbstractConsoleCommand implements ConsoleCommand {

    private static final String infoMessage = "'pr' - show file content: " +
            "pr <file name>";
    private static final Pattern suggestPattern = Pattern.compile("\\s*[pr]{1,2}\\s*",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern validatePattern = Pattern.compile("^\\s*pr\\s+([_0-9.a-z/\\\\]+)\\s*$",
            Pattern.CASE_INSENSITIVE);

    public Print() {
        super(infoMessage, suggestPattern, validatePattern);
    }

    @Override
    public CommandResult execute(Path current, String input) throws IOException {
        String fileName = null;
        Matcher m = validatePattern.matcher(input);
        while (m.find()) {
            fileName = m.group(1);
        }
        if (fileName != null) {
            Path newPath = current.resolve(fileName);
            if (Files.exists(newPath)) {
                List<String> lines = Files.readAllLines(current.resolve(fileName));
                String message = String.join("\n", lines);
                return new CommandResult(true, current, message);
            }
        }
        String message = "File wasn't found. Files: \n"
                + DirectoryUtils.getInternalFiles(current);
        return new CommandResult(false, current, message);
    }
}
