package ru.ulstu.is.sbapp.stringchange.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class AllLetters implements Stringchange{
    private final Logger log = LoggerFactory.getLogger(LastLetter.class);

    @Override
    public String change(String word) {
        return word.toUpperCase(Locale.ROOT);
    }

    public void init() { log.info("AllLetters.init()"); }

    public void destroy() { log.info("AllLetters.destroy()"); }

}
