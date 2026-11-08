package ma.emsi.akodad.tp2AkodadTests;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;

public class Test1 {

    public static void main(String[] args) {

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName("gemini-3.5-flash")
                .temperature(0.7)
                .build();

        String response = model.chat("Bonjour, peux-tu te présenter brièvement ?");
        System.out.println(response);
        String response2 = model.chat("Quelle est l'heure actuelle ?");
        System.out.println(response2);
        String response3 = model.chat("Bonjour, je m'appelle Houssam. Présente-toi.");
        System.out.println(response3);

        String response4 = model.chat("Comment je m'appelle ?");
        System.out.println(response4);
    }
}