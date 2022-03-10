package ru.ulstu.is.sbapp.stringchange.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class FirstLetter implements Stringchange{
    private final Logger log = LoggerFactory.getLogger(FirstLetter.class);

    @Override
    public String change(String word) {
        return StringUtils.capitalize(word);
    }

    public void init() { log.info("FirstLetter.init()"); }

    public void destroy() { log.info("FirstLetter.destroy()"); }
}
