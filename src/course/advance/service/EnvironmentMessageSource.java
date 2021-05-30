package course.advance.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class EnvironmentMessageSource implements MessageSource {

    private final Locale defaultLocale;

    public EnvironmentMessageSource(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    @Override
    public String getMessage(String key, Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", locale);
        if (rb == null)
            rb = ResourceBundle.getBundle("MessageBundle", defaultLocale);
        return rb.getString(key);
    }
}
