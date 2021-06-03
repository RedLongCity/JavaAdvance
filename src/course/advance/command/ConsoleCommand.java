package course.advance.command;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import course.advance.command.result.CommandResult;

import java.io.IOException;
import java.nio.file.Path;

public interface ConsoleCommand {

    /**
     * @return info about command
     */
    String getInfo();

    /**
     * @param input user's input
     * @return possibility that user meant this command
     */
    boolean suggest(@NotNull String input);

    /**
     * @param input user's input
     * @return if used current command
     */
    boolean validate(@Nullable String input);

    /**
     * @param current start path
     * @param input   user's input
     * @return successful or failed result with message
     * @throws IOException IOException
     */
    CommandResult execute(Path current, String input) throws IOException;
}
