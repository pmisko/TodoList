package pl.pmisko.Languages;

import org.junit.Assert;
import org.junit.Test;

public class LanguageDTOTest {
    Language testLanguage=new Language(4,"TestMsg", "ex");

    @Test
    public void shouldReturnLanguageDTOWithId4AndCodeExWhenPassTestLanguageToTheConstructor() {
        LanguageDTO languageDTO;
        final Integer expectedId=4;
        final String expectedCode="ex";

        languageDTO=new LanguageDTO(testLanguage);

        final String resultCode = languageDTO.getCode();
        final Integer resultId = languageDTO.getId();

        Assert.assertEquals(expectedCode,resultCode);
        Assert.assertEquals(expectedId, resultId);
    }
}