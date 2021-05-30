package course.advance.dto;

import course.advance.exception.WrongParamException;

import java.util.Properties;

/**
 * Pudge (funny implementation of DOTA 2 Hero)
 * https://dota2.fandom.com/ru/wiki/Pudge
 */
public class Pudge {

    private final String name;
    /**
     * Should be between 1 - 30
     */
    private final int lvl;

    private final Properties phrases;

    public Pudge(String name, int lvl, Properties phrases) throws WrongParamException {
        this.name = name;
        if (lvl < 1 || lvl > 30)
            throw new WrongParamException("[INCORRECT LVL]: level should be between 1 and 30");
        this.lvl = lvl;
        this.phrases = phrases;
    }

    @Override
    public String toString() {
        return "Pudge{" +
                "name='" + name + '\'' +
                ", lvl=" + lvl +
                ", phrases=" + phrases +
                '}';
    }

    public int getLvl() {
        return lvl;
    }

    public String getName() {
        return name;
    }
}
