package pl.pmisko.Languages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class LanguagesServiceTest {

    @Mock
    private LanguageRepository repository;
    private LanguagesService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new LanguagesService(repository);
    }

    @Test
    public void findAll() {
        final List<Language> languageList = Arrays.asList(
                new Language(1, "testMsg", "pl"),
                new Language(2, "testMsg2", "de"));
        Mockito.when(repository.findAll()).thenReturn(languageList);

        final int size = 2;

        List<LanguageDTO> result;

        result = service.findAll();

        assertEquals(size, result.size());
        assertEquals(languageList.get(0).getId(), result.get(0).getId());
        assertEquals(languageList.get(1).getId(), result.get(1).getId());
        assertEquals(languageList.get(1).getCode(), result.get(1).getCode());
        assertEquals(languageList.get(1).getCode(), result.get(1).getCode());
    }
}