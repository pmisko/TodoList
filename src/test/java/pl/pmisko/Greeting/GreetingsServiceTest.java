package pl.pmisko.Greeting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.pmisko.Languages.Language;
import pl.pmisko.Languages.LanguageRepository;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class GreetingsServiceTest {

    @Mock
    private LanguageRepository repository;
    private GreetingsService greetingsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        greetingsService= new GreetingsService(repository);
    }

    @Test
    public void prepareGreetingShouldReturnHelloWorldWhenNameIsEmptyStringAndLanguageIsNull() {
        final String greeting= "Hello world!";

        final String result = greetingsService.prepareGreeting("", null);

        Assert.assertEquals(greeting,result);
    }

    @Test
    public void greetingsServiceShouldReturnExpectedStringWhenNameParamIsAnnaAndLangEqualsTwo() {
        final String testName= "Anna";
        final Integer testLang= 2;
        Mockito.when(repository.findById(2))
                .thenReturn(Optional.of(new Language(2, "Guten Tag", "de")));
        final String expected= "Guten Tag Anna!";

        final String result= greetingsService.prepareGreeting(testName,testLang);

        Assert.assertEquals(expected, result);
    }
}