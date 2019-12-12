package pl.pmisko.Languages;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class LanguagesService {
    private LanguageRepository repository;

    LanguagesService(LanguageRepository repository) {
        this.repository= repository;
    }

    List<LanguageDTO> findAll () {
        final List<LanguageDTO> collect = repository.findAll()
                .stream()
                .map(LanguageDTO::new)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
        return collect;
    }
}
