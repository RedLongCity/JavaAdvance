package course.advance;

import course.advance.dao.PudgeEnvironmentRepository;
import course.advance.dao.PudgeRepository;
import course.advance.dto.Pudge;
import course.advance.exception.DataAccessException;
import course.advance.exception.WrongParamException;
import course.advance.service.EnvironmentMessageSource;
import course.advance.service.MessageSource;
import course.advance.service.PudgeService;
import course.advance.service.PudgeServiceImpl;

import java.util.Locale;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern inputDataPattern = Pattern.compile("^[а-яa-z]+,\\d+(,[a-z]+)?$",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        try {
            if (args.length < 2 || !inputDataPattern.matcher(String.join(",", args)).find())
                throw new WrongParamException("NOT VALID INPUT PARAMS FOR CREATING PUDGE");
            PudgeService pudgeService = new PudgeServiceImpl(getMessageSource(), getPudgeRepository());
            Pudge pudge = pudgeService.extractPudgeFromFile(getLocale(args));
            if (pudge != null)
                System.out.println("Successfully extracted Pudge: " + pudge);
            else {
                pudge = initPudgeAndSave(args, pudgeService);
                System.out.println("Pudge created from params: " + pudge);
            }
        } catch (WrongParamException e) {
            System.err.println("INCORRECT INPUT DATA!");
            System.err.println("SET ARGS Like 'Pudge Name (only text)' required, " +
                    "'Pudge Lvl (1-30)' required, 'Locale (ru, de, fr)' not required");
        } catch (DataAccessException e) {
            System.err.println("EXCEPTION WHILE EXTRACTING/SAVING FILE");
        }
    }

    private static MessageSource getMessageSource() {
        return new EnvironmentMessageSource(new Locale("en", "EN"));
    }

    private static PudgeRepository getPudgeRepository() {
        return new PudgeEnvironmentRepository(System.getProperty("user.dir") + "/" + "pudge.bin");
    }

    private static Pudge initPudgeAndSave(String[] args, PudgeService pudgeService)
            throws DataAccessException, WrongParamException {
        Pudge pudge = pudgeService.initPudge(args[0], Integer.parseInt(args[1]), getLocale(args));
        return pudgeService.savePudgeToFile(pudge);
    }

    private static Locale getLocale(String[] args) {
        if (args.length > 2)
            switch (args[2]) {
                case "fr":
                    return new Locale("fr", "FR");
                case "de":
                    return new Locale("de", "DE");
            }
        return new Locale("ru", "RU");
    }
}
