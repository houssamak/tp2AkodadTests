package ma.emsi.akodad.tp2AkodadTests;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

/**
 * Le RAG facile !
 */
public class Test5 {

    interface Assistant {
        String chat(String userMessage);
    }

    public static void main(String[] args) {

        String llmKey = System.getenv("GEMINI_API_KEY");
        if (llmKey == null) {
            System.out.println("La variable d'environnement GEMINI_API_KEY n'est pas définie.");
            return;
        }

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(llmKey)
                .modelName("gemini-3.5-flash")
                .temperature(0.3)
                .build();

        String nomDocument = "infos.txt";
        Document document = FileSystemDocumentLoader.loadDocument(nomDocument);

        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        Assistant assistant =
                AiServices.builder(Assistant.class)
                        .chatModel(model)
                        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                        .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
                        .build();

        String question = "Quelle est la capitale de la France ?";

        String reponse = assistant.chat(question);

        System.out.println(reponse);
    }
}