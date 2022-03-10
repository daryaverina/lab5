package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.stringchange.service.StringchangeService;

@SpringBootTest
class SbappApplicationTests {
	@Autowired
	StringchangeService stringchangeService;

	@Test
	void testFirstLetter(){
		final String rez = stringchangeService.change("улгу", "first");
		Assertions.assertEquals("Улгу", rez);
	}

	@Test
	void testLastLetter(){
		final String rez = stringchangeService.change("улгу", "last");
		Assertions.assertEquals("улгУ", rez);
	}

	@Test
	void testAllLetters(){
		final String rez = stringchangeService.change("улгу", "all");
		Assertions.assertEquals("УЛГУ", rez);
	}

	@Test
	void testStringchangeErrorWired() {
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> stringchangeService.change("улгту", "fi"));
	}
}
