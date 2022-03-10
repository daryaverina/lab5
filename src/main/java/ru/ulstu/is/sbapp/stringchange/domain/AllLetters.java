package ru.ulstu.is.sbapp.stringchange.domain;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component(value = "all")
public class AllLetters implements Stringchange{
    @Override
    public String change(String word) {
        return word.toUpperCase(Locale.ROOT);
    }
}