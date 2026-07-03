package com.sunanda.aiproductsearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiApi;

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAiApi openAiApi() {
        // API key will be read from environment variable OPENAI_API_KEY
        return new OpenAiApi(System.getenv("OPENAI_API_KEY"));
    }

    @Bean
    public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi) {
        return new OpenAiChatModel(openAiApi);
    }
}
