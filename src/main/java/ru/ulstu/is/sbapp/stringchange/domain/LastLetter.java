package ru.ulstu.is.sbapp.stringchange.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class LastLetter implements Stringchange{
    private final Logger log = LoggerFactory.getLogger(LastLetter.class);

    @Override
    public String change(String word) {
        char[] newword = new char[word.length()];
        word.getChars(0, word.length() - 1, newword, 0);
        newword[word.length() - 1] = (char) (word.charAt(word.length() - 1) - 32);
        return String.valueOf(newword);
    }

    public void init() { log.info("LastLetter.init()"); }

    public void destroy() { log.info("LastLetter.destroy()"); }
}
