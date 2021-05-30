package course.advance.service;

import course.advance.dto.Pudge;
import course.advance.exception.DataAccessException;
import course.advance.exception.WrongParamException;

import java.util.Locale;

public interface PudgeService {

    Pudge extractPudgeFromFile(Locale locale) throws WrongParamException, DataAccessException;

    Pudge savePudgeToFile(Pudge pudge) throws DataAccessException;

    Pudge initPudge(String name, int lvl, Locale locale) throws WrongParamException;

}
