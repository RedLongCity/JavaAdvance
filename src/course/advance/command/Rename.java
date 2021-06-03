package course.advance.command;

import course.advance.command.result.CommandResult;
import course.advance.utils.DirectoryUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command for renaming files or directories
 */
public class Rename extends AbstractConsoleCommand implements ConsoleCommand {

    private static final String infoMessage = "'ren' - rename file or directory: " +
            "ren <old file name> <new file name>";
    private static final Pattern suggestPattern = Pattern.compile("^\\s*[ren]{1,3}\\s*$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern validatePattern = Pattern.compile("^\\s*ren\\s+([_0-9.a-z/\\\\]+)\\s+([_0-9.a-z/\\\\]+)\\s*$",
            Pattern.CASE_INSENSITIVE);

    public Rename() {
        super(infoMessage, suggestPattern, validatePattern);
    }

    @Override
    public CommandResult execute(Path current, String input) throws IOException {
        String firstFile = null;
        String secondFile = null;
        Matcher m = validatePattern.matcher(input);
        while (m.find()) {
            firstFile = m.group(1);
            secondFile = m.group(2);
        }
        if (firstFile != null && secondFile != null) {
            Path original = current.resolve(firstFile);
            if (Files.exists(original)) {
                Files.move(current.resolve(firstFile), current.resolve(secondFile));
                return new CommandResult(true, current, "File was successfully renamed.");
            }
        }
        String message = "File wasn't found. Files: \n"
                + DirectoryUtils.getInternalFiles(current);
        return new CommandResult(false, current, message);
    }

}
