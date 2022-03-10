package ru.ulstu.is.sbapp.stringchange.domain;
import org.springframework.stereotype.Component;

@Component(value = "last")
public class LastLetter implements Stringchange{
    @Override
    public String change(String word) {
        char[] newword = new char[word.length()];
        word.getChars(0, word.length() - 1, newword, 0);
        newword[word.length() - 1] = (char) (word.charAt(word.length() - 1) - 32);
        return String.valueOf(newword);
    }
}
