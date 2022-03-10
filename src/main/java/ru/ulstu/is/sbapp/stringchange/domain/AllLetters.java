package ru.ulstu.is.sbapp.stringchange.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class AllLetters implements Stringchange{
    @Override
    public String change(String word) {
        return word.toUpperCase(Locale.ROOT);
    }
}
