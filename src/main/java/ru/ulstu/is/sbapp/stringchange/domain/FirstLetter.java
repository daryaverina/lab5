package ru.ulstu.is.sbapp.stringchange.domain;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component(value = "first")
public class FirstLetter implements Stringchange{

    @Override
    public String change(String word) {
        return StringUtils.capitalize(word);
    }
}
