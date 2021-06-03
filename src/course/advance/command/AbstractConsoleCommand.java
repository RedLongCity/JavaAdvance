package course.advance.command;

import java.util.regex.Pattern;

public abstract class AbstractConsoleCommand implements ConsoleCommand {

    private final String infoMessage;
    private final Pattern validatePattern;
    private final Pattern suggestPattern;

    protected AbstractConsoleCommand(String infoMessage,
                                     Pattern suggestPattern,
                                     Pattern validatePattern) {
        this.infoMessage = infoMessage;
        this.suggestPattern = suggestPattern;
        this.validatePattern = validatePattern;
    }

    @Override
    public String getInfo() {
        return infoMessage;
    }

    @Override
    public boolean suggest(String input) {
        return suggestPattern.matcher(input).find();
    }

    @Override
    public boolean validate(String input) {
        return validatePattern.matcher(input).find();
    }

}
