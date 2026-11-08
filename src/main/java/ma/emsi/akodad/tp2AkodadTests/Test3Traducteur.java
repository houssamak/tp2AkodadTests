
package ma.emsi.akodad.tp2AkodadTests;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test3Traducteur {

    public static void main(String[] args) {

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName("gemini-3.5-flash")
                .temperature(0.7)
                .build();

        // Premier template : une seule variable (le texte)
        PromptTemplate template1 = PromptTemplate.from(
                "Traduis le texte suivant en anglais : {{it}}"
        );

        Prompt prompt1 = template1.apply("Bonjour, comment allez-vous ?");

        System.out.println("Première traduction :");
        System.out.println(model.chat(prompt1.toUserMessage()));

        // Deuxième template : deux variables (langue et texte)
        PromptTemplate template2 = PromptTemplate.from(
                "Traduis le texte suivant en {{langue}} : {{texte}}"
        );

        Prompt prompt2 = template2.apply(Map.of(
                "langue", "espagnol",
                "texte", "Bonjour, comment allez-vous ?"
        ));

        System.out.println("\nDeuxième traduction :");
        System.out.println(model.chat(prompt2.toUserMessage()));
    }
}
