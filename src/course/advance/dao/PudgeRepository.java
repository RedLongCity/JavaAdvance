package course.advance.dao;

import course.advance.dto.Pudge;
import course.advance.exception.DataAccessException;
import course.advance.exception.WrongParamException;

public interface PudgeRepository {

    Pudge extract() throws DataAccessException, WrongParamException;

    Pudge save(Pudge pudge) throws DataAccessException;
}
