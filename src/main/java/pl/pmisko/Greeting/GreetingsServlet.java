package pl.pmisko.Greeting;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
class GreetingsServlet {

    private final Logger logger = Logger.getLogger(GreetingsServlet.class);

    GreetingsService service;

    GreetingsServlet(GreetingsService service) {
        this.service = service;
    }

    @GetMapping(value = "/api", params = {"language", "name"})
    @ResponseBody
    String welcome(@RequestParam(value = "name") String name,
                   @RequestParam(value = "language") Integer lang) {

        logger.info("Got request with parameters");

        return service.prepareGreeting(name, lang);
    }
}

