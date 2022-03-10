package ru.ulstu.is.sbapp.stringchange.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class FirstLetter implements Stringchange{

    @Override
    public String change(String word) {
        return StringUtils.capitalize(word);
    }
}
