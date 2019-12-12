package pl.pmisko.Greeting;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.pmisko.Languages.Language;
import pl.pmisko.Languages.LanguageRepository;

import java.util.Optional;
@Service
public class GreetingsService {
    public static final String DEFAULT_NAME = "world";
    public static final Language DEFAULT_LANGUAGE = new Language(1, "Hello", "en");
    private final Logger logger = Logger.getLogger(GreetingsService.class);

    private LanguageRepository repository;

    GreetingsService(LanguageRepository repository) {
        this.repository = repository;
    }

    public String prepareGreeting(String name, Integer langId) {
        langId = Optional.ofNullable(langId).orElse(DEFAULT_LANGUAGE.getId());
        String welcomeMsg = repository.findById(langId).orElse(DEFAULT_LANGUAGE).getWelcomeMsg();
        if (name.isEmpty()) {
            name = DEFAULT_NAME;
        }
        String nameToWelcome = Optional.of(name).orElse(DEFAULT_NAME);
        System.out.println(nameToWelcome);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
