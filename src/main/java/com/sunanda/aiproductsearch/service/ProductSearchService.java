package com.sunanda.aiproductsearch.service;

import com.sunanda.aiproductsearch.model.Product;
import com.sunanda.aiproductsearch.repository.ProductRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;
    private final OpenAiChatModel chatModel;

    public ProductSearchService(ProductRepository productRepository,
                                OpenAiChatModel chatModel) {
        this.productRepository = productRepository;
        this.chatModel = chatModel;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String semanticSearch(String query) {
        List<Product> products = productRepository.findAll();

        StringBuilder context = new StringBuilder();
        for (Product p : products) {
            context.append("Product: ").append(p.getName())
                   .append(" | Category: ").append(p.getCategory())
                   .append(" | Description: ").append(p.getDescription())
                   .append("\n");
        }

        String template = """
                You are a product search assistant.
                Here is the product catalog:
                {catalog}

                User query: {query}

                Based on the catalog, suggest the best matching products and explain why.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(
                java.util.Map.of("catalog", context.toString(), "query", query)
        );

        return chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
