package course.advance.command;

import course.advance.command.result.CommandResult;
import course.advance.utils.DirectoryUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeDirectory extends AbstractConsoleCommand implements ConsoleCommand {

    private static final String infoMessage = "'cd' - command for changing directory. " +
            "Example cd ../ for moving to parent dir or cd <some directory>";
    private static final Pattern suggestPattern = Pattern.compile("(^\\s*[cd]){1,2}\\s*",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern validatePattern = Pattern.compile("^cd\\s+([a-z/\\\\.]+)$",
            Pattern.CASE_INSENSITIVE);

    public ChangeDirectory() {
        super(infoMessage, suggestPattern, validatePattern);
    }

    @Override
    public CommandResult execute(Path current, String input) throws IOException {
        String dest = null;
        Matcher m = validatePattern.matcher(input);
        while (m.find()) {
            dest = m.group(1);
        }
        if (dest != null) {
            Path newPath = current.resolve(dest);
            if (Files.exists(newPath))
                return new CommandResult(true, newPath, newPath.normalize().toString());
        }
        String messsage = "Directory wasn't found. Directories: \n"
                + DirectoryUtils.getInternalDirectories(current);
        return new CommandResult(false, current, messsage);
    }
}
