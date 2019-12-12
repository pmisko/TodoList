package pl.pmisko.Languages;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class LanguagesServlet {

    private final Logger logger = Logger.getLogger(LanguagesServlet.class);

    private LanguagesService service;

    LanguagesServlet(LanguagesService service) {
        this.service = service;
    }

    @GetMapping("/langs")
    protected ResponseEntity<List<LanguageDTO>> findAllLangs() {
        logger.info("Got request");

        return ResponseEntity.ok(service.findAll());
    }
}
