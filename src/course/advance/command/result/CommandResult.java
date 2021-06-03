package course.advance.command.result;

import java.nio.file.Path;

public class CommandResult {

    private final boolean successful;
    private final Path newPath;
    private final String message;

    public CommandResult(boolean successful,
                         Path newPath,
                         String message) {
        this.successful = successful;
        this.newPath = newPath;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public Path getNewPath() {
        return newPath;
    }

    public String getMessage() {
        return message;
    }
}
