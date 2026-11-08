
package ma.emsi.akodad.tp2AkodadTests;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.store.embedding.CosineSimilarity;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel;
import java.time.Duration;

public class Test4Embeddings {

    public static void main(String[] args) {

        EmbeddingModel model = new BgeSmallEnV15EmbeddingModel();

        EmbeddingModel googleModel = GoogleGenAiEmbeddingModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName("gemini-embedding-001")
                .taskType(GoogleGenAiEmbeddingModel.TaskTypeEnum.SEMANTIC_SIMILARITY)
                .timeout(Duration.ofSeconds(30))
                .build();
        String[][] phrases = {
                {"A cat is sleeping on the sofa.",
                        "A kitten is resting on the couch."},

                {"I love this movie.",
                        "I hate this movie."},

                {"The car is very fast.",
                        "The automobile moves quickly."},

                {"The weather is sunny today.",
                        "The computer needs more memory."},

                {"She is studying computer science.",
                        "She is learning programming."},

                {"The dog is playing in the garden.",
                        "The airplane is flying above the ocean."}
        };

        for (int i = 0; i < phrases.length; i++) {

            // Modèle BGE
            Embedding embedding1 = model.embed(phrases[i][0]).content();
            Embedding embedding2 = model.embed(phrases[i][1]).content();

            double similarityBGE = CosineSimilarity.between(
                    embedding1,
                    embedding2
            );

            // Modèle Google Gemini
            Embedding googleEmbedding1 = googleModel.embed(phrases[i][0]).content();
            Embedding googleEmbedding2 = googleModel.embed(phrases[i][1]).content();

            double similarityGoogle = CosineSimilarity.between(
                    googleEmbedding1,
                    googleEmbedding2
            );

            System.out.println("\nPaire " + (i + 1));
            System.out.println("Phrase 1 : " + phrases[i][0]);
            System.out.println("Phrase 2 : " + phrases[i][1]);
            System.out.println("Similarité BGE : " + similarityBGE);
            System.out.println("Similarité Google : " + similarityGoogle);
        }
    }
}
