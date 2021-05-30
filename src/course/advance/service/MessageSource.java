package course.advance.service;

import java.util.Locale;

public interface MessageSource {

    String getMessage(String key, Locale locale);
}
