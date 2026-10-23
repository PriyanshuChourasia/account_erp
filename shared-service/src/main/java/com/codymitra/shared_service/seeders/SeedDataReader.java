package com.codymitra.shared_service.seeders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class SeedDataReader {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * Reads a JSON array from the classpath into a list.
     * Example path: "com/codymitra/shared_service/modules/account_nature/data/account_nature.json"
     */
    public <T> List<T> readList(String classpathLocation, Class<T> type) {
        try (InputStream inputStream = new ClassPathResource(classpathLocation).getInputStream()) {
            return objectMapper.readValue(
                    inputStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, type)
            );
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read seed data: " + classpathLocation, e);
        }
    }
}
