package edu.bsu.cs222;
import edu.bsu.cs222.english.dictionary.DefinitionParser;
import edu.bsu.cs222.english.dictionary.SynonymParser;
import edu.bsu.cs222.language.translator.LanguageOutputFormatter;
import edu.bsu.cs222.language.translator.translators.chinese.translators.ChineseToGermanTranslator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
public class FormatterTests {
    @Test
    public void outputFormatterFormatsDefinitionTest() throws IOException {
        Assertions.assertEquals("a state of well-being and contentment : joy, a pleasurable or satisfying experience, felicity,  aptness", new OutputFormatter().formatOutputForUser(new DefinitionParser("happiness").parseForDefinition()));
    }
    @Test
    public void outputFormatterFormatsSynonymTest() throws IOException{
        Assertions.assertEquals("fluky, fortuitous, fortunate, heaven-sent, lucky, providential, blissful, chuffed, delighted, glad, gratified, joyful, joyous, pleased, satisfied, thankful, tickled, content, contented, gratified, pleased, satisfied, fortunate, lucky, applicable, appropriate, apt, becoming, befitting, felicitous, fit, fitted, fitting, good, meet, pretty, proper, right, suitable, hung up, obsessed, queer", new OutputFormatter().formatOutputForUser(new SynonymParser("happy").parseForSynonyms()));
    }
    @Test
    public void languageOutputFormatterFormatsResultTest(){
        Assertions.assertTrue(new LanguageOutputFormatter("one", "two", "three").formatOutput().startsWith("O"));
    }
    @Test
    public void chineseToGermanTranslatorGivesWordInGermanTest() throws IOException, InterruptedException {
        Assertions.assertEquals("wetter", new ChineseToGermanTranslator("天气").getTranslatedWordInGerman());
    }
}
