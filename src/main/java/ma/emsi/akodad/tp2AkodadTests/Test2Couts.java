package ma.emsi.akodad.tp2AkodadTests;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.data.message.UserMessage;
public class Test2Couts {

    public static void main(String[] args) {

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .build();

        ChatResponse response = model.chat(
                UserMessage.from("Bonjour, peux-tu te présenter brièvement ?")
        );

        System.out.println("Réponse :");
        System.out.println(response.aiMessage().text());

        System.out.println("\nStatistiques des tokens :");
        System.out.println("Tokens en entrée : " + response.tokenUsage().inputTokenCount());
        System.out.println("Tokens en sortie : " + response.tokenUsage().outputTokenCount());
        System.out.println("Total : " + response.tokenUsage().totalTokenCount());
        double coutEntree = response.tokenUsage().inputTokenCount() * 0.30 / 1_000_000;
        double coutSortie = response.tokenUsage().outputTokenCount() * 2.50 / 1_000_000;
        double coutTotal = coutEntree + coutSortie;

        System.out.println("Coût estimé : " + coutTotal + " $");
        System.out.println("Nombre de requêtes pour 1 $ : " + (int) (1 / coutTotal));
    }
}