package course.advance.service;

import course.advance.dao.PudgeRepository;
import course.advance.dto.Pudge;
import course.advance.exception.DataAccessException;
import course.advance.exception.WrongParamException;

import java.util.Locale;
import java.util.Properties;

public class PudgeServiceImpl implements PudgeService {

    private final MessageSource messageSource;
    private final PudgeRepository repository;
    private final String[] messages = {"phrase1", "phrase2", "phrase3"};

    public PudgeServiceImpl(MessageSource messageSource,
                            PudgeRepository repository) {
        this.messageSource = messageSource;
        this.repository = repository;
    }

    @Override
    public Pudge extractPudgeFromFile(Locale locale) throws WrongParamException, DataAccessException {
        Pudge extracted = repository.extract();
        if (extracted != null)
            extracted = new Pudge(extracted.getName(), extracted.getLvl(), getLangProperties(locale));
        return extracted;
    }

    @Override
    public Pudge savePudgeToFile(Pudge pudge) throws DataAccessException {
        return repository.save(pudge);
    }

    @Override
    public Pudge initPudge(String name, int lvl, Locale locale) throws WrongParamException {
        return new Pudge(name, lvl, getLangProperties(locale));
    }

    private Properties getLangProperties(Locale locale) {
        Properties result = new Properties();
        for (String key : messages)
            result.setProperty(key, messageSource.getMessage(key, locale));
        return result;
    }
}
