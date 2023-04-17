package edu.bsu.cs222.language.translator.translators.german.translators;
import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.ErrorHandler;
import edu.bsu.cs222.language.translator.TranslationConnection;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
public class GermanToFrenchTranslator {
    private final String frenchWordToTranslateToGerman;
    private final String wordTranslatedToGerman;
    public GermanToFrenchTranslator(String userWordToTranslate) throws IOException, InterruptedException {
        this.frenchWordToTranslateToGerman = userWordToTranslate;
        this.wordTranslatedToGerman = findWordTranslatedToGerman(requestTranslation());
    }
    private String requestTranslation() throws IOException, InterruptedException {
        TranslationConnection.createLanguageList();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "f263b8ed6amshcf56c5fd7c784c4p128de1jsna14a7e815ea4")
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("source_language=fr&target_language=de&text="+frenchWordToTranslateToGerman))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public String findWordTranslatedToGerman(String translationResponse){
        try{
            HashMap<String, String> result = JsonPath.parse(translationResponse).json();
            JSONArray jsonResultArray = JsonPath.read(result, "$..translatedText");
            String responseWord = jsonResultArray.get(0).toString();
            if(jsonResultArray.get(0).toString().equals(frenchWordToTranslateToGerman)){
                ErrorHandler.throwWordNotFoundError();
            }
            return responseWord.toLowerCase();
        }catch(Error e){
            return e.getMessage();
        }
    }
    public String getTranslatedWordInGerman(){
        return wordTranslatedToGerman;
    }
}
